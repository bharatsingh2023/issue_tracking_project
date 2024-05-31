package com.issue.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.issue.entity.IssueDetailsEntity;

public interface IssueDetailsRepo extends JpaRepository<IssueDetailsEntity, Integer> {

}
