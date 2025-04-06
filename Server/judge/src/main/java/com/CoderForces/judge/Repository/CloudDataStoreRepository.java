package com.CoderForces.judge.Repository;

public interface CloudDataStoreRepository {
    public boolean readAndSaveToLocal(String sourceFilePath,String destinationFilePath);

    public boolean write(String data,String filePath);
}
