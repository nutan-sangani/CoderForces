package com.CoderForces.judge.Controller;

import com.CoderForces.judge.Entity.Problem;
import com.CoderForces.judge.Service.DatabaseService;
import com.CoderForces.judge.Service.S3FileOperationsService;
import jakarta.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    DatabaseService databaseService;

    @Autowired
    S3FileOperationsService s3FileOperationsService;
    @GetMapping("/all")
    public List<Problem> getAllProblems(){
        return databaseService.getAllProblems();
    }

    @GetMapping("/data/{problemId}")
    public String getProblemData(@PathVariable("problemId")long problemId){
        String data = s3FileOperationsService.readFromFile("problem/statement/"+problemId+".txt");
//        System.out.println("problem/statement/"+problemId+".txt");
//        System.out.println(problemId);
        return data;
    }
}
