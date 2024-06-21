package com.CoderForces.judge.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

@Component
public class FilePathUtil {
    private HashMap<String,String> filePathMapping = new HashMap<>();
    int flag=0;

    @Value("${DockerFilePath.java}")
    private String javaImageDockerFilePath;

    @Value("${DockerFilePath.cpp}")
    private String cppImageDockerFilePath;

    @Value("${Submission.code}")
    private String submissionCodeFilePath;

    @Value("${Submission.input}")
    private String problemInputFilePath;

    @Value("${Submission.output}")
    private String submissionOutputFilePath;

    @Value("${Problem.answer}")
    private String problemAnswerFilePath;

    private void setFields(){
        filePathMapping.put("javaImageDockerFile",javaImageDockerFilePath);
        filePathMapping.put("cppImageDockerFile",cppImageDockerFilePath);
        filePathMapping.put("submissionCodeFile",submissionCodeFilePath);
        filePathMapping.put("submissionOutputFile",submissionOutputFilePath);
        filePathMapping.put("problemInputFile",problemInputFilePath);
        filePathMapping.put("problemAnsweFile",problemAnswerFilePath);
        flag=1;
    }

    FilePathUtil(){
        flag=0;
    }

    public String getFilePath(String key){
        if(flag==0)
            setFields();
        return filePathMapping.get(key);
    }

}
