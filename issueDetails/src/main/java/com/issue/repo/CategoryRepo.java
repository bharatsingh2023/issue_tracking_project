package com.issue.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.issue.entity.IssueCategoryEntity;

public interface CategoryRepo extends JpaRepository<IssueCategoryEntity, Integer> {

}
