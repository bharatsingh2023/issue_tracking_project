package com.beas.issuelist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.beas.issuelist.entity.IssueDetailsEntity;
import com.beas.issuelist.service.IssueServiceImpl;

@RestController
public class IssueListController {

	@Autowired
	private IssueServiceImpl issueServiceImpl;
	@GetMapping("/getallissue/{projectid}")
	public ResponseEntity<List<IssueDetailsEntity>> getIssues(@PathVariable("projectid") String projectid ,Sort sort) {
		List<IssueDetailsEntity> allissue=null;
		 allissue= issueServiceImpl.getissuedetails(projectid, null, sort);
		
		return ResponseEntity.ok(allissue);
	}
	
	@GetMapping("/getallissue/{projectid}/{sortparameter}")
	public ResponseEntity<List<IssueDetailsEntity>> getIssuesSorted(@PathVariable("projectid") String projectid,@PathVariable(value = "sortparameter",required = false) String sortparameter ,Sort sort) {
		List<IssueDetailsEntity> allissue=null;
		allissue= issueServiceImpl.getissuedetails(projectid, sortparameter, sort);
		
		List<IssueDetailsEntity> list = allissue.stream().filter(filter->filter.getProjectid()==Integer.parseInt(projectid)).toList();
		
		return ResponseEntity.ok(list);
	}
}
