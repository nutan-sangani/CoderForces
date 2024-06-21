package com.CoderForces.judge.Config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.api.model.WaitResponse;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.Closeable;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.CountDownLatch;

@Configuration
public class DockerConfig {
    @Autowired
    private Environment environment;
    private DockerClientConfig getDockerClientConfig(String connectionUrl){
        return DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(connectionUrl)
                .build();
    }

    private DockerHttpClient getDockerHttpClient(DockerClientConfig config){
        return new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .maxConnections(10)
                .connectionTimeout(Duration.ofSeconds(20))
                .responseTimeout(Duration.ofSeconds(20))
                .build();
    }

    @Bean
    public DockerClient getDockerClient(){
        String connectionUrl = environment.getProperty("dockerHost.url");
        DockerClientConfig config = this.getDockerClientConfig(connectionUrl);
        DockerHttpClient httpClient = getDockerHttpClient(config);
        return DockerClientImpl.getInstance(config,httpClient);
    }
    public ResultCallback<WaitResponse> waitCallBack(CountDownLatch latch){
        return new ResultCallback<WaitResponse>() {
            @Override
            public void onStart(Closeable closeable) {
            }
            @Override
            public void onNext(WaitResponse waitResponse) {
            }
            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable);
            }
            @Override
            public void onComplete() {
                latch.countDown();
            }
            @Override
            public void close() throws IOException {

            }
        };
    }
    public ResultCallback<Frame> getResultCallBack(StringBuilder output,CountDownLatch latch){
        return new ResultCallback<Frame>(){
            @Override
            public void close() throws IOException {
            }
            @Override
            public void onStart(Closeable closeable) {
            }
            @Override
            public void onNext(Frame frame) {
                appendFormattedFrameToOutput(output,frame);
            }
            @Override
            public void onError(Throwable throwable) {
                //todo handle errors properly.
                System.out.println(throwable.toString());
            }
            @Override
            public void onComplete() {
                latch.countDown();
                removeLastNewlineCharacter(output);
            }
        };
    }

    private void appendFormattedFrameToOutput(StringBuilder output,Frame frame){
        output.append(frame).append("\n");
    }

    private void removeLastNewlineCharacter(StringBuilder output){
        output.replace(output.length()-1,output.length(),"");
    }
}