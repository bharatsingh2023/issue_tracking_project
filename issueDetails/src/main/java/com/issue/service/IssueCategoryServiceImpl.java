package com.issue.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.issue.entity.IssueCategoryEntity;
import com.issue.repo.CategoryRepo;

@Service
public class IssueCategoryServiceImpl implements IssueCategoryServiceInterface {

	private final CategoryRepo categoryRepo;
	
	
	
	
	public IssueCategoryServiceImpl(CategoryRepo categoryRepo) {
		super();
		this.categoryRepo = categoryRepo;
	}




	@Override
	public List<IssueCategoryEntity> getAll() {

		
		return categoryRepo.findAll();
	}

}
