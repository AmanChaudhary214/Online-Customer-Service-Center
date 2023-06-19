package com.ocsc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocsc.Entity.CurrentAdminSession;

public interface CurrentAdminSessionRepository extends JpaRepository<CurrentAdminSession, Integer> {

	public CurrentAdminSession findByToken(String token);
	
}
