package com.issue.service;

import org.springframework.stereotype.Service;

import com.issue.entity.IssueDetailsEntity;
import com.issue.repo.IssueDetailsRepo;

@Service
public class IssueServiceImpl implements IssueListServiceInterface {

	
	private final IssueDetailsRepo issueDetailsRepo;
	

	
	public IssueServiceImpl(IssueDetailsRepo issueDetailsRepo) {
		super();
		this.issueDetailsRepo = issueDetailsRepo;
	}



	@Override
	public IssueDetailsEntity createIssue(IssueDetailsEntity issues) {
	
		return issueDetailsRepo.save(issues);
	}

}
