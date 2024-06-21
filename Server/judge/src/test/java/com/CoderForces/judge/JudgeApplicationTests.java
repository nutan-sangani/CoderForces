package com.CoderForces.judge;

import com.CoderForces.judge.Entity.Problem;
import com.CoderForces.judge.Entity.Submission;
import com.CoderForces.judge.Repository.ProblemRepository;
import com.CoderForces.judge.Repository.SubmissionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@SpringBootTest
class JudgeApplicationTests {

	@Autowired
	ProblemRepository problemRepository;

	@Autowired
	SubmissionRepository submissionRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void saveProblem(){
		Problem problem = Problem.builder()
				.problemId(4)
				.timeStamp(Timestamp.valueOf(LocalDateTime.now()))
				.build();

		problemRepository.save(problem);
	}

	@Test
	void saveSubmission(){
		Problem problem = Problem.builder()
				.problemId(1)
				.timeStamp(Timestamp.valueOf(LocalDateTime.now()))
				.build();

		Submission submission = Submission.builder()
				.submissionId(6)
				.language("java")
				.isAccepted("running")
				.At(Timestamp.valueOf(LocalDateTime.now()))
				.problem(problem)
				.build();

		Optional<Submission> submission1 = submissionRepository.findById(2L);
		System.out.println(submission1.get().getAt());
		submission1.get().setLanguage("cppp");
		submissionRepository.save(submission1.get());

		submissionRepository.save(submission);
	}
}
