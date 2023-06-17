package com.ocsc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocsc.Entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
