package com.CoderForces.judge.Service.Impl;

import com.CoderForces.judge.Entity.Submission;
import com.CoderForces.judge.Service.DatabaseService;
import com.CoderForces.judge.Service.JudgeService;
import com.CoderForces.judge.Service.SqsService;
import com.sun.jdi.event.ExceptionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.model.Message;

import java.util.List;

import static java.lang.Thread.sleep;

@Component
public class WorkerServiceImpl {

    @Autowired
    SqsService sqsService;

    @Autowired
    JudgeService judgeService;

    @Autowired
    DatabaseService databaseService;

    @Value("${aws.sqs.url}")
    String sqsUrl;

//    @Scheduled(fixedDelay = 500)
    public void processQueue(){
        List<Message> messages = sqsService.getFromQueue(sqsUrl);
        System.out.println("worker running");
        if(!messages.isEmpty()){
            messages.forEach((message) -> {
                long submissionId = Long.parseLong(message.body());
                System.out.println("worker service: "+submissionId);
                Submission submissionObject = databaseService.getProblemIdFromSubmissionId(submissionId).get(); //see if ye pura problem obj deta h ya nhi.
                String verdict = judgeService.runCode(submissionObject.getProblem().getProblemId(),submissionId,submissionObject.getLanguage());
                databaseService.updateSubmissionStatus(submissionId,verdict);
                sqsService.deleteMessageFromQueue(message,sqsUrl);
            });
        }
    }
}
