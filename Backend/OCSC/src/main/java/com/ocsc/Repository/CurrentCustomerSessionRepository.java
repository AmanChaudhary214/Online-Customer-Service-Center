package com.ocsc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocsc.Entity.CurrentCustomerSession;

public interface CurrentCustomerSessionRepository extends JpaRepository<CurrentCustomerSession, Integer> {
	
	public CurrentCustomerSession findByToken(String token);
	
}
