package com.ocsc.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocsc.Entity.Customer;
import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Optional<Customer> findByEmail(String email);
	
	public Customer findByUsername(String userName);

	List<Customer> findByName(String name);
	
}
