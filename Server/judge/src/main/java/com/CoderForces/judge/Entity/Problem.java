package com.CoderForces.judge.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Problem {
    @Id
    @SequenceGenerator(
            name = "problemIdGenerator",
            sequenceName = "problemId_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "problemIdGenerator"
    )
    private long problemId;
    @Column(name = "At")
    private Timestamp timeStamp;
    @Column(
            length = 500
    )
    private String name;
}
