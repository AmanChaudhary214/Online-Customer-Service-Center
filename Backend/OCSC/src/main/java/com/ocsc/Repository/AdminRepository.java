package com.ocsc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocsc.Entity.Admin;
import com.ocsc.Entity.Customer;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	public Admin findByUsername(String userName);
	
}
