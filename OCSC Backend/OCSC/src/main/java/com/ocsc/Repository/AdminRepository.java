package com.ocsc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocsc.Entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
