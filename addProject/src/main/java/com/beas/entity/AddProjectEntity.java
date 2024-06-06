package com.beas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "issue_tracker_projects_tbl")
public class AddProjectEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int project_id;
	public String project_duration;
	public String project_name;
	public String start_date;
	public String team_size;
	public String creation_date;

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getProject_duration() {
		return project_duration;
	}

	public void setProject_duration(String project_duration) {
		this.project_duration = project_duration;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getTeam_size() {
		return team_size;
	}

	public void setTeam_size(String team_size) {
		this.team_size = team_size;
	}

	public String getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}

	public AddProjectEntity(int project_id, String project_duration, String project_name, String start_date,
			String team_size, String creation_date) {
		super();
		this.project_id = project_id;
		this.project_duration = project_duration;
		this.project_name = project_name;
		this.start_date = start_date;
		this.team_size = team_size;
		this.creation_date = creation_date;
	}

	public AddProjectEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
