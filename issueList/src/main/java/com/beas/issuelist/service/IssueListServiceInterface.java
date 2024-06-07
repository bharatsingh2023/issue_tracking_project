package com.beas.issuelist.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.beas.issuelist.entity.IssueDetailsEntity;

public interface IssueListServiceInterface {

	List<IssueDetailsEntity> getissuedetails(String projectid, String sortparameter, Sort sort);

	IssueDetailsEntity update(IssueDetailsEntity issue);

	
}
