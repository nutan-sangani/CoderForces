package com.CoderForces.judge.Service.Impl;

import com.CoderForces.judge.Model.InputCode;
import com.CoderForces.judge.Service.*;
import com.CoderForces.judge.Util.FilePathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JudgeServiceImpl implements JudgeService {
    @Autowired
    FileOperationsService fileOperationsService;

    @Autowired
    S3FileOperationsService s3FileOperationsService;

    @Autowired
    DockerService dockerService;

    @Autowired
    SqsService sqsService;

    @Autowired
    OutputTesterService outputTesterService;

    @Autowired
    DatabaseService databaseService;

    @Autowired
    FilePathUtil filePathUtil;

    @Value("${aws.sqs.url}")
    String sqsUrl;

    public String sendCodeRunRequest(InputCode code){
        try{
            code.setCode(code.getCode().replace('`','"'));
            long submissionId = databaseService.createSubmissionAndSave(code);
            s3FileOperationsService.saveToFile(code.getCode(),"submission/"+submissionId+".txt");
            sqsService.addToQueue(String.valueOf(submissionId),sqsUrl);
            return String.valueOf(submissionId);
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    private String executeCodeAndGetOutput(String submissionLanguage){
        String imageDockerFilePath = filePathUtil.getFilePath(submissionLanguage+"ImageDockerFile");
        String imageId = dockerService.buildImage(imageDockerFilePath);

        String containerId = dockerService.createContainer(imageId);
        dockerService.startContainer(containerId);
        String output = dockerService.getContainerLogs(containerId);

        dockerService.removeContainer(containerId);
        dockerService.removeImage(imageId);

        System.out.println(output);
        return output;
    }

    @Override
    public String runCode(long problemId,long submissionId,String submissionLanguage){
        String submissionCode = s3FileOperationsService.readFromFile("submission/"+submissionId+".txt");

        String submissionCodeFilePath = filePathUtil.getFilePath("submissionCodeFile");
        fileOperationsService.saveToFile(submissionCode,submissionCodeFilePath);

        String problemInput = s3FileOperationsService.readFromFile("problem/input/"+problemId+".txt");

        String problemInputFilePath = filePathUtil.getFilePath("problemInputFile");
        fileOperationsService.saveToFile(problemInput,problemInputFilePath);

        String output = executeCodeAndGetOutput(submissionLanguage);

        String problemAnswer = s3FileOperationsService.readFromFile("problem/output/"+problemId+".txt");
        boolean isCorrect = outputTesterService.testOutput(output,problemAnswer);

        System.out.println("isCorrect : "+isCorrect);
        if(isCorrect) return "Accepted";
        else return "Rejected";
    }

    public String test(){
        return "Testing";
    }
}
