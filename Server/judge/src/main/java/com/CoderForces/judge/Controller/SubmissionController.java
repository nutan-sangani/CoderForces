package com.CoderForces.judge.Controller;

import com.CoderForces.judge.Entity.Problem;
import com.CoderForces.judge.Model.SubmissionDetails;
import com.CoderForces.judge.Repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.CoderForces.judge.Entity.Submission;

import java.util.Optional;

@RestController
@RequestMapping("/submission")
public class SubmissionController {

    @Autowired
    SubmissionRepository submissionRepository;

    @GetMapping("/details/{submissionId}")
    public SubmissionDetails getSubmissionDetails(@PathVariable("submissionId") Long submissionId){
         Optional<Submission> submission = submissionRepository.findById(submissionId);
         System.out.println(submission);
         Problem problem = submission.get().getProblem();
         return SubmissionDetails.builder()
                 .acceptedStatus(submission.get().getIsAccepted())
                 .submissionLanguage(submission.get().getLanguage())
                 .problemId(problem.getProblemId())
                 .submissionId(submissionId)
                 .problemName(problem.getName())
                 .submissionTime(submission.get().getAt().toString())
                 .build();
    }
}
