package com.beas.issuelist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.beas.issuelist.entity.IssueDetailsEntity;
import com.beas.issuelist.repo.IssueDetailsRepo;
import com.beas.issuelist.service.IssueListServiceInterface;
import com.beas.issuelist.service.IssueServiceImpl;

@RestController
@CrossOrigin(origins = "*")
public class IssueListController {

	@Autowired
	private IssueServiceImpl issueServiceImpl;
	@Autowired
	IssueListServiceInterface issueListServiceInterface;
	@Autowired
	private IssueDetailsRepo issueDetailsRepo;

	@GetMapping("/{projectid}")
	public ResponseEntity<List<IssueDetailsEntity>> getIssues(@PathVariable("projectid") String projectid, Sort sort) {

		System.err.println("-->projectid " + projectid);
		List<IssueDetailsEntity> allissue = null;
		allissue = issueServiceImpl.getissuedetails(projectid, null, sort);

		return ResponseEntity.ok(allissue);
	}

	@PostMapping("/updateIssueList")
	public ResponseEntity<IssueDetailsEntity> updateIssueList(@RequestBody IssueDetailsEntity issue) {
		
		return ResponseEntity.ok(issueListServiceInterface.update(issue));
	}

	@GetMapping("/getallissue/{projectid}/{sortparameter}")
	public ResponseEntity<List<IssueDetailsEntity>> getIssuesSorted(@PathVariable("projectid") String projectid,
			@PathVariable(value = "sortparameter", required = false) String sortparameter, Sort sort) {
		List<IssueDetailsEntity> allissue = null;
		allissue = issueServiceImpl.getissuedetails(projectid, sortparameter, sort);

		List<IssueDetailsEntity> list = allissue.stream()
				.filter(filter -> filter.getProjectid() == Integer.parseInt(projectid)).toList();

		return ResponseEntity.ok(list);
	}
	
	@DeleteMapping("/delete/{issueId}")
	public ResponseEntity<String> deletePremisesById(@PathVariable int issueId) {
	    try {
	    	issueDetailsRepo.deleteById(issueId);
	        return ResponseEntity.ok("issue deleted successfully");
	    } catch (EmptyResultDataAccessException e) {
	        return ResponseEntity.notFound().build(); 
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Failed to delete issue");
	    }
	}
	
}
