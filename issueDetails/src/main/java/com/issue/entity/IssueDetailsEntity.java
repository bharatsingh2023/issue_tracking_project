package com.issue.entity;

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
	private int issue_id;
	private String logged_by;
	private String logged_On;
	private String issue_category;
	private String issue_status;
	@Lob
    @Column(columnDefinition = "TEXT")
	private String description;
	private String comments;
	private String fixed_by;
	private int project_id;
	
	
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getFixed_by() {
		return fixed_by;
	}
	public void setFixed_by(String fixed_by) {
		this.fixed_by = fixed_by;
	}
	public int getIssue_id() {
		return issue_id;
	}
	public void setIssue_id(int issue_id) {
		this.issue_id = issue_id;
	}
	public String getLogged_by() {
		return logged_by;
	}
	public void setLogged_by(String logged_by) {
		this.logged_by = logged_by;
	}
	public String getLogged_On() {
		return logged_On;
	}
	public void setLogged_On(String logged_On) {
		this.logged_On = logged_On;
	}
	public String getIssue_category() {
		return issue_category;
	}
	public void setIssue_category(String issue_category) {
		this.issue_category = issue_category;
	}
	public String getIssue_status() {
		return issue_status;
	}
	public void setIssue_status(String issue_status) {
		this.issue_status = issue_status;
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
	
	
	
	@Override
	public String toString() {
		return "IssueDetailsEntity [issue_id=" + issue_id + ", logged_by=" + logged_by + ", logged_On=" + logged_On
				+ ", issue_category=" + issue_category + ", issue_status=" + issue_status + ", description="
				+ description + ", comments=" + comments + ", fixed_by=" + fixed_by + ", project_id=" + project_id
				+ "]";
	}
	public IssueDetailsEntity() {
		// TODO Auto-generated constructor stub
	}
	
}
