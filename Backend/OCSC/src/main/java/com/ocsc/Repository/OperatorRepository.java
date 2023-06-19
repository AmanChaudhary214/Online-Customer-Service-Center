package com.ocsc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocsc.Entity.Operator;

public interface OperatorRepository extends JpaRepository<Operator, Integer> {

	public Operator findByUserName(String userName);
	
}
