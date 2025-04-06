package com.CoderForces.judge.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class AwsConfig {
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
    @Scope("singleton")
    public StaticCredentialsProvider cloudCredentialProvider(){
        AwsBasicCredentials credentials = getAwsCredentials();
        return StaticCredentialsProvider.create(credentials);
    }

    @Bean
    @Scope("singleton")
    public S3Client getS3Client(StaticCredentialsProvider credentialsProvider){
        return S3Client.builder()
                .credentialsProvider(credentialsProvider)
                .region(Region.AP_SOUTH_1)
                .build();
    }

    @Bean
    @Scope("singleton")
    public SqsClient getSqsClient(StaticCredentialsProvider credentialsProvider){
        return SqsClient.builder()
                .credentialsProvider(credentialsProvider)
                .region(Region.AP_SOUTH_1)
                .build();
    }
}
