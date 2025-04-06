package com.CoderForces.judge.Repository;

import java.util.List;

public interface LocalFileOperationsRepository {
    public List<String> read(String filePath);
    public boolean write(String dataToBeWritten,String filePath);
}
