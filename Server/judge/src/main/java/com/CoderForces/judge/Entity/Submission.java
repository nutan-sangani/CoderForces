package com.CoderForces.judge.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Submission{
    @Id
    @SequenceGenerator(
            name = "submission_id_generator",
            sequenceName="submissionId_sequence",
            allocationSize = 1
            )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "submission_id_generator"
    )
    private long submissionId;
    private String language;
    private Timestamp At;
    private String isAccepted;

    @ManyToOne
    @JoinColumn(
            name = "problem_id"
    )
    private Problem problem;
}
