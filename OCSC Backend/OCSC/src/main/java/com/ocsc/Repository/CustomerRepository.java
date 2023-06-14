package com.ocsc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocsc.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
