package com.CoderForces.judge.Repository;

import com.CoderForces.judge.Entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends JpaRepository<Problem,Long> {

}
