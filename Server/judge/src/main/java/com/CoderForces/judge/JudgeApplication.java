package com.CoderForces.judge;

import com.CoderForces.judge.Entity.Problem;
import com.CoderForces.judge.Repository.ProblemRepository;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JudgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JudgeApplication.class, args);
		System.out.println("Om ganeshay namah");
	}
}
