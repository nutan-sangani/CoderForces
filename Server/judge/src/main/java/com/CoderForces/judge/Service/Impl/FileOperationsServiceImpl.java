package com.CoderForces.judge.Service.Impl;

import com.CoderForces.judge.Model.InputCode;
import com.CoderForces.judge.Service.FileOperationsService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileOperationsServiceImpl implements FileOperationsService{
    //a probable bottleneck is the no of file descriptors available in linux, but since rated limited
    //it is not a problem.
    @Override
    public void saveToFile(String code, String filePath) {
        //buffered writer since large files likhne pad sakte h.
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            writer.write(code);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<String> readFromFile(String filePath){
        List<String> fileContent = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = bufferedReader.readLine())!=null){
                fileContent.add(line);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return fileContent;
    }

}
