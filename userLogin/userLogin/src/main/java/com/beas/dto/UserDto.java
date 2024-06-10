package com.beas.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UserDto {
@NotEmpty
private String name;
@Email(message = "Username must be a valid email address")
@Column(unique = true)
@NotEmpty(message = "Username is mandatory")
private String username;
@NotEmpty(message = "Password is mandatory")
private String password;
private String creationDate;



public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getCreationDate() {
	return creationDate;
}
public void setCreationDate(String creationDate) {
	this.creationDate = creationDate;
}
@Override
public String toString() {
	return "UserDto [username=" + username + ", password=" + password + ", creationDate=" + creationDate + "]";
}


}
