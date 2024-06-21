package com.CoderForces.judge.Service.Impl;

import com.CoderForces.judge.Model.InputCode;
import com.CoderForces.judge.Service.DockerService;
import com.CoderForces.judge.Config.DockerConfig;
import com.CoderForces.judge.Util.FilePathUtil;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.api.model.WaitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.Closeable;
import java.io.File;
import java.util.concurrent.CountDownLatch;

@Service
public class DockerServiceImpl implements DockerService {
    @Autowired
    public DockerClient dockerClient;
    @Autowired
    public DockerConfig dockerConfig;
    @Autowired
    public Environment environment;

    @Autowired
    public FilePathUtil filePathUtil;

    @Override
    public String buildImage(String imageDockerFilePath){
        File file = new File(imageDockerFilePath);
        return dockerClient.buildImageCmd(file)
                .exec(new BuildImageResultCallback(){
                    @Override
                    public void onStart(Closeable stream) {
                        super.onStart(stream);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        super.onError(throwable);
                    }
                })
                .awaitImageId();
    }
    @Override
    public String createContainer(String imageId) {
        CreateContainerResponse response = dockerClient.createContainerCmd(imageId).exec();
        return response.getId();
    }

    @Override
    public void startContainer(String containerId) {
        dockerClient.startContainerCmd(containerId).exec();
        CountDownLatch latch = new CountDownLatch(1);
        try{
            ResultCallback<WaitResponse> waitCallBack = dockerConfig.waitCallBack(latch);
            dockerClient.waitContainerCmd(containerId).exec(waitCallBack);
            latch.await();
        }
        catch(Exception e){
            System.out.println(e);
        }

    }

    @Override
    public String getContainerLogs(String containerId) {
        StringBuilder output = new StringBuilder();
        try{
            CountDownLatch latch = new CountDownLatch(1);
            ResultCallback<Frame> resultCallback = dockerConfig.getResultCallBack(output,latch);
            dockerClient.logContainerCmd(containerId)
                    .withStdOut(true)
                    .withStdErr(true)
                    .exec(resultCallback);
            latch.await();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return output.toString()
                .replace("STDOUT: ","")
                .replaceFirst("STDERR: ourCode.java:","");
        //removing stderr and stdout tags, which are included by docker-java in the logs.
    }
    public void removeContainer(String containerId){
        dockerClient.removeContainerCmd(containerId).withForce(true).exec();
    }
    public void removeImage(String imageId){
        dockerClient.removeImageCmd(imageId).withForce(true).exec();
    }

}
