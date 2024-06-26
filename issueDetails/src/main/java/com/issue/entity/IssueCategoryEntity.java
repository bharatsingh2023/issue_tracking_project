package com.issue.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "issue_category")
public class IssueCategoryEntity {

	@Id
	private int category_id;
	private String category_name;
	private String creation_date;
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	
	
	
}
