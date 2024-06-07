package com.beas.issuelist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.beas.issuelist.entity.IssueDetailsEntity;
import com.beas.issuelist.repo.IssueDetailsRepo;

@Service
public class IssueServiceImpl implements IssueListServiceInterface {

	@Autowired
	private IssueDetailsRepo issueDetailsRepo;

	@Override
	public List<IssueDetailsEntity> getissuedetails(String projectid,String sortparameter,Sort sort) {
		
		List<IssueDetailsEntity> allissue=null;
		if(sortparameter==null && sort.isUnsorted()) {
			allissue=issueDetailsRepo.getallissue(projectid);
			
		} else {
			Sort and = Sort.by(sortparameter).and(sort);
			allissue=issueDetailsRepo.findAll(and);
		}
		return allissue;
	}

	@Override
	public IssueDetailsEntity update(IssueDetailsEntity issue) {
			
		return issueDetailsRepo.save(issue);
	}
	

	
}
