package com.CoderForces.judge.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InputCode {
    private String code;
    private String language;
    private long problemId;
    private long userId;
}
