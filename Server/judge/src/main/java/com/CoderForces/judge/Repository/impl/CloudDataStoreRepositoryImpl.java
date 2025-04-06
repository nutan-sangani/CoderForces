package com.CoderForces.judge.Repository.impl;

import com.CoderForces.judge.Repository.CloudDataStoreRepository;
import com.CoderForces.judge.Repository.LocalFileOperationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Repository
public class CloudDataStoreRepositoryImpl implements CloudDataStoreRepository {
    @Autowired
    private S3Client s3Client;

    @Autowired
    private LocalFileOperationsRepository localFileOperationsRepository;

    @Value("${aws.bucketName}")
    private String awsBucketName;

    @Override
    public boolean readAndSaveToLocal(String sourceFilePath, String destinationFilePath) {
        try{
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(awsBucketName)
                    .key(sourceFilePath)
                    .build();

            ResponseBytes<GetObjectResponse> getObjectResponse = s3Client.getObjectAsBytes(getObjectRequest);
            localFileOperationsRepository.write(getObjectResponse.asUtf8String(), destinationFilePath);
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean write(String data, String filePath) {
        try{
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(awsBucketName)
                    .key(filePath)
                    .build();
            s3Client.putObject(putObjectRequest, RequestBody.fromString(data));
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
