package com.CoderForces.judge.Model;


import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class SubmissionDetails {
    private long submissionId;
    private String submissionLanguage;
    private String problemName;
    private String acceptedStatus;
    private long problemId;
    private String submissionTime;
}
