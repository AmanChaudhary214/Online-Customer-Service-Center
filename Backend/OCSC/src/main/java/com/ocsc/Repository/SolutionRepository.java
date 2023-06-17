package com.ocsc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocsc.Entity.Solution;

public interface SolutionRepository extends JpaRepository<Solution, Integer> {

}
