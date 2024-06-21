package com.CoderForces.judge.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class AwsConfig {

    @Autowired
    public Environment environment;

    @Value("${aws.accessKey}")
    private String accessKey;

    @Value("${aws.secretKey}")
    private String secretAccessKey;

    public AwsBasicCredentials getAwsCredentials(){
        return AwsBasicCredentials.builder()
                .accessKeyId(accessKey)
                .secretAccessKey(secretAccessKey)
                .build();
    }

    @Bean
    public S3Client getS3Client(){
        AwsBasicCredentials awsCredentials = getAwsCredentials();
        return S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .region(Region.AP_SOUTH_1)
                .build();
    }

    @Bean
    public SqsClient getSqsClient(){
        AwsBasicCredentials awsBasicCredentials = getAwsCredentials();
        return SqsClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
                .region(Region.AP_SOUTH_1)
                .build();
    }
}
