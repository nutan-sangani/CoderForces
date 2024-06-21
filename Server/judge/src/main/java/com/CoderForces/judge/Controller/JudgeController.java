package com.CoderForces.judge.Controller;

import com.CoderForces.judge.Model.InputCode;
import com.CoderForces.judge.Service.JudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/judge")
public class JudgeController {

    @Autowired
    private JudgeService judgeService;

    @PostMapping("/submit")
    public ResponseEntity<String> submitCode(@RequestBody InputCode code){
        String status = judgeService.sendCodeRunRequest(code);
        return new ResponseEntity<String>(status,HttpStatus.OK);
    }

    @PostMapping("/test")
    public ResponseEntity<String> testCode(){
        String verdict = judgeService.test();
        return new ResponseEntity<String>(verdict,HttpStatus.OK);
    }
}
