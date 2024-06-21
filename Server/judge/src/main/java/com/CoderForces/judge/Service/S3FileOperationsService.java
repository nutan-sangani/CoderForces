package com.CoderForces.judge.Service;

import com.CoderForces.judge.Model.InputCode;

import java.util.List;

public interface S3FileOperationsService {
    public void saveToFile(String inputCode,String filePath);

    public String readFromFile(String fileName);
}
