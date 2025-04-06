package com.CoderForces.judge.Repository.impl;

import com.CoderForces.judge.Repository.LocalFileOperationsRepository;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LocalFileOperationsRepositoryImpl implements LocalFileOperationsRepository {
    public boolean write(String dataToBeWritten, String filePath) {
        //buffered writer since large files likhne pad sakte h.
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            writer.write(dataToBeWritten);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<String> read(String filePath){
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
