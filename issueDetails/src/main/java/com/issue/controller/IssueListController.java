package com.issue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.issue.dto.IssueDto;
import com.issue.entity.IssueCategoryEntity;
import com.issue.entity.IssueDetailsEntity;
import com.issue.service.IssueCategoryServiceImpl;
import com.issue.service.IssueListServiceInterface;

@RestController
@CrossOrigin(origins = "*")
public class IssueListController {

	
	private IssueListServiceInterface issueListServiceInterface;
	@Autowired
	private IssueCategoryServiceImpl issueCategoryServiceImpl;

	public IssueListController(IssueListServiceInterface issueListServiceInterface) {
		super();
		this.issueListServiceInterface = issueListServiceInterface;
	}
	
	
	@PostMapping("/submitIssue")
	public ResponseEntity<IssueDetailsEntity> createIssue(@RequestBody IssueDto issues ){
		
		return ResponseEntity.ok(issueListServiceInterface.createIssue(issues));
	}
	
	@GetMapping("/getIssueCategory")
	public ResponseEntity<List<IssueCategoryEntity>> getIssueCategory() {
		
		return ResponseEntity.ok(issueCategoryServiceImpl.getAll());
	}
	
	
}
