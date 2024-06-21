package com.CoderForces.judge.Service.Impl;

import com.CoderForces.judge.Service.FileOperationsService;
import com.CoderForces.judge.Service.S3FileOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class S3FileOperationsServiceImpl implements S3FileOperationsService {

    @Autowired
    S3Client s3Client;
    @Value("${aws.bucketName}")
    private String awsBucketName;
    @Override
    public void saveToFile(String inputCode, String filePath) {
        try{
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(awsBucketName)
                    .key(filePath)
                    .build();
            s3Client.putObject(putObjectRequest, RequestBody.fromString(inputCode));
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public String readFromFile(String filePath) {
        try{
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(awsBucketName)
                    .key(filePath)
                    .build();

            ResponseBytes<GetObjectResponse> getObjectResponse= s3Client.getObjectAsBytes(getObjectRequest);
            return getObjectResponse.asUtf8String();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return "error";
    }
}
