package com.CoderForces.judge.Service;

import com.CoderForces.judge.Model.InputCode;

public interface JudgeService {

    public String runCode(long problemId,long submissionId,String submissionLanguage);

    public String test();

    public String sendCodeRunRequest(InputCode code);
}
