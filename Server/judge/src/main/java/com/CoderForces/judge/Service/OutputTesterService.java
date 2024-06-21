package com.CoderForces.judge.Service;

import java.util.List;

public interface OutputTesterService {
    public boolean testOutput(String output, String answer);

    public boolean testOutput(List<String> output, List<String> answer);
}
