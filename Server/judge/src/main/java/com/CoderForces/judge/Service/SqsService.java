package com.CoderForces.judge.Service;

import software.amazon.awssdk.services.sqs.model.Message;

import java.util.List;

public interface SqsService {
    public void addToQueue(String message,String queueUrl);

    public List<Message> getFromQueue(String queueUrl);

    public void deleteMessageFromQueue(Message message,String queueUrl);
}
