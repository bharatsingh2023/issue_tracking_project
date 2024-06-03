package com.issue.service;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.issue.dto.IssueDto;
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
	public IssueDetailsEntity createIssue(IssueDto issues) {
		
		
		IssueDetailsEntity issueDetailsEntity = new IssueDetailsEntity();
		BeanUtils.copyProperties(issues, issueDetailsEntity);
		issueDetailsEntity.setLogged_On(String.valueOf(new Date()));
		issueDetailsEntity.setProject_id(Integer.parseInt(issues.getProject_id()));
		return issueDetailsRepo.save(issueDetailsEntity);
	}

}
