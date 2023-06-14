package com.ocsc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocsc.Entity.Call;

public interface CallRepository extends JpaRepository<Call, Integer>{

}
