package com.CoderForces.judge.Service;

import com.CoderForces.judge.Model.InputCode;

import java.util.List;

public interface FileOperationsService {
    public void saveToFile(String inputCode,String filePath);

    public List<String> readFromFile(String filePath);
}
