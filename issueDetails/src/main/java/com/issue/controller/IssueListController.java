package com.issue.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.issue.entity.IssueDetailsEntity;
import com.issue.service.IssueListServiceInterface;

@RestController
public class IssueListController {

	
	private IssueListServiceInterface issueListServiceInterface;

	public IssueListController(IssueListServiceInterface issueListServiceInterface) {
		super();
		this.issueListServiceInterface = issueListServiceInterface;
	}
	
	
	@PostMapping("/submitIssue")
	public ResponseEntity<IssueDetailsEntity> createIssue(@RequestBody IssueDetailsEntity issues ){
		
		return ResponseEntity.ok(issueListServiceInterface.createIssue(issues));
	}
	
	
}
