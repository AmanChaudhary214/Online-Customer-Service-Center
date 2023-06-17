package com.ocsc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ocsc.Entity.Issue;

public interface IssueRepository extends JpaRepository<Issue, Integer> {

}
