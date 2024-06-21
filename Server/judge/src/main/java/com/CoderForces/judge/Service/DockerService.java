package com.CoderForces.judge.Service;

import com.CoderForces.judge.Model.InputCode;

public interface DockerService {
    public String buildImage(String imageDockerFilePath);

    public String createContainer(String imageId);

    public void startContainer(String containerId);

    public String getContainerLogs(String containerId);

    public void removeContainer(String containedId);
    public void removeImage(String imageId);
}
