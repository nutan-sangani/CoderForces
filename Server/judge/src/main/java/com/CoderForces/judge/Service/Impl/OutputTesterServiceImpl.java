package com.CoderForces.judge.Service.Impl;

import com.CoderForces.judge.Service.OutputTesterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutputTesterServiceImpl implements OutputTesterService {
    @Override
    public boolean testOutput(List<String> output, List<String> answer) {
        long n = answer.size();
        if(n!=output.size()) return false;
        for(int i=0;i<n;i++){
            if(!output.get(i).equals(answer.get(i))) return false;
        }
        return true;
    }

    @Override
    public boolean testOutput(String output, String answer){
        output = output.replace("\r\n", "\n").replace("\r", "\n");
        answer = answer.replace("\r\n", "\n").replace("\r", "\n");

        // Trim whitespace
        output = output.trim();
        answer = answer.trim();
        long n = answer.length();
        System.out.println(output);
        if(output.contains("Passed")){
            return true;
        }
        return false;
//        if(output.length()!=n)
//        {
//            System.out.println("length not matching");
//            return false;
//        }
//        for(int i=0;i<n;i++){
//            if(output.charAt(i)!=answer.charAt(i))
//            {
//                System.out.println("Mismatch at index " + i + ":");
//                System.out.println("Output char: " + (int) output.charAt(i) + " ('" + output.charAt(i) + "')");
//                System.out.println("Answer char: " + (int) answer.charAt(i) + " ('" + answer.charAt(i) + "')");
//                return false;
//            }
//        }
//        return true;
    }
}
