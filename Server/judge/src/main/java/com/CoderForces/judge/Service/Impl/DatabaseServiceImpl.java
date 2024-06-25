package com.CoderForces.judge.Service.Impl;

import com.CoderForces.judge.Entity.Problem;
import com.CoderForces.judge.Entity.Submission;
import com.CoderForces.judge.Model.InputCode;
import com.CoderForces.judge.Repository.ProblemRepository;
import com.CoderForces.judge.Repository.SubmissionRepository;
import com.CoderForces.judge.Service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public long createSubmissionAndSave(InputCode code) {
        String defaultSubmissionStatus = "Running";
        Submission submission = Submission.builder()
                .At(Timestamp.valueOf(LocalDateTime.now()))
                .submissionId(Long.MAX_VALUE)
                .language(code.getLanguage())
                .problem(Problem.builder().problemId(code.getProblemId()).build())
                .isAccepted(defaultSubmissionStatus)
                .build();
        Submission submissionSaved = submissionRepository.save(submission);
        return submissionSaved.getSubmissionId();
    }

    public Optional<Submission> getProblemIdFromSubmissionId(Long submissionId){
        return submissionRepository.findById(submissionId);
    }

    public void updateSubmissionStatus(Long submissionId,String status){
        Optional<Submission> submission = submissionRepository.findById(submissionId);
        submission.get().setIsAccepted(status);
        submissionRepository.save(submission.get());
    }

    @Override
    public List<Problem> getAllProblems(){
        return problemRepository.findAll();
    }
}
