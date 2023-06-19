package com.ocsc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocsc.Entity.CurrentOperatorSession;

public interface CurrentOperatorSessionRepository extends JpaRepository<CurrentOperatorSession, Integer> {

	public CurrentOperatorSession findByToken(String token);
	
}
