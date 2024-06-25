package com.CoderForces.judge.Service;

import com.CoderForces.judge.Entity.Problem;
import com.CoderForces.judge.Entity.Submission;
import com.CoderForces.judge.Model.InputCode;

import java.util.List;
import java.util.Optional;

public interface DatabaseService {
    public long createSubmissionAndSave(InputCode code);

    public Optional<Submission> getProblemIdFromSubmissionId(Long submissionId);

    public void updateSubmissionStatus(Long submissionId,String status);

    public List<Problem> getAllProblems();
}
