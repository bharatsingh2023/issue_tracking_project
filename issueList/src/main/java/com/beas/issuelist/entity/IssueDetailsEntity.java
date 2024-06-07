package com.beas.issuelist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "issue_tracker_issues_tbl")
public class IssueDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "issue_id")
	private int issueid;
	
	@Column(name = "logged_by")
	private String loggedby;
	
	@Column(name = "logged_On")
	private String loggedOn;
	
	@Column(name = "issue_category")
	private String issuecategory;
	
	@Column(name = "issue_status")
	private String issuestatus;
	@Lob
    @Column(columnDefinition = "TEXT")
	private String description;
	
	
	@Column(name = "comments")
	private String comments;
	
	
	@Column(name = "fixed_by")
	private String fixedby;
	
	@Column(name = "project_id")
    private int projectid;
	
	
	public int getIssueid() {
		return issueid;
	}




	public IssueDetailsEntity(int issueid, String loggedby, String loggedOn, String issuecategory, String issuestatus,
			String description, String comments, String fixedby, int projectid) {
		super();
		this.issueid = issueid;
		this.loggedby = loggedby;
		this.loggedOn = loggedOn;
		this.issuecategory = issuecategory;
		this.issuestatus = issuestatus;
		this.description = description;
		this.comments = comments;
		this.fixedby = fixedby;
		this.projectid = projectid;
	}




	public void setIssueid(int issueid) {
		this.issueid = issueid;
	}




	public String getLoggedby() {
		return loggedby;
	}




	public void setLoggedby(String loggedby) {
		this.loggedby = loggedby;
	}




	public String getLoggedOn() {
		return loggedOn;
	}




	public void setLoggedOn(String loggedOn) {
		this.loggedOn = loggedOn;
	}




	public String getIssuecategory() {
		return issuecategory;
	}




	public void setIssuecategory(String issuecategory) {
		this.issuecategory = issuecategory;
	}




	public String getIssuestatus() {
		return issuestatus;
	}




	public void setIssuestatus(String issuestatus) {
		this.issuestatus = issuestatus;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public String getComments() {
		return comments;
	}




	public void setComments(String comments) {
		this.comments = comments;
	}




	public String getFixedby() {
		return fixedby;
	}




	public void setFixedby(String fixedby) {
		this.fixedby = fixedby;
	}




	public int getProjectid() {
		return projectid;
	}




	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}




	public IssueDetailsEntity() {
		// TODO Auto-generated constructor stub
	}
	
}
