package com.CoderForces.judge.Service.Impl;

import com.CoderForces.judge.Service.SqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.List;
import java.util.UUID;

@Service
public class SqsServiceImpl implements SqsService {

    @Autowired
    SqsClient sqsClient;

    @Override
    public void addToQueue(String message, String queueUrl) {
        String randomNumber = UUID.randomUUID().toString();
        SendMessageRequest sendMessageRequest = SendMessageRequest
                .builder()
                .messageBody(message)
                .queueUrl(queueUrl)
                .messageGroupId("submissionId")
                .messageDeduplicationId(randomNumber)
                .build();

        sqsClient.sendMessage(sendMessageRequest);
    }

    @Override
    public void deleteMessageFromQueue(Message message,String queueUrl){
        DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest
                .builder()
                .queueUrl(queueUrl)
                .receiptHandle(message.receiptHandle())
                .build();
        sqsClient.deleteMessage(deleteMessageRequest);
    }

    @Override
    public List<Message> getFromQueue(String queueUrl) {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest
                .builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(3)
                .waitTimeSeconds(10)
                .build();
        ReceiveMessageResponse receiveMessageResponse = sqsClient.receiveMessage(receiveMessageRequest);
        return receiveMessageResponse.messages();
    }
}
