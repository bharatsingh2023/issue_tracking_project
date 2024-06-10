package com.beas.entities;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class User implements UserDetails{

	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)	
private int id;
@NotEmpty
private String name;
@Email(message = "Username must be a valid email address")
@NotEmpty(message = "Username is mandatory")
@Column(unique = true)
private String username;
@NotEmpty(message = "Password is mandatory")
private String password;
@Column(name = "creation_date")
private String creationDate;
private boolean verified;
//private boolean online;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}


public String getName() {
	return name;
}

public boolean isVerified() {
	return verified;
}
public void setVerified(boolean verified) {
	this.verified = verified;
}
public void setName(String name) {
	this.name = name;
}
public void setUsername(String username) {
	this.username = username;
}
public void setPassword(String password) {
	this.password = password;
}
public String CreationDate() {
	return creationDate;
}
public void setCreationDate(String creationDate) {
	this.creationDate = creationDate;
}

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	
	return null;
}
@Override
public String toString() {
	return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", creationDate="
			+ creationDate + "]";
}
@Override
public String getPassword() {
	
	return this.password;
}
@Override
public String getUsername() {
	
	return this.username;
}
@Override
public boolean isAccountNonExpired() {
	
	return true;
}
@Override
public boolean isAccountNonLocked() {
	
	return true;
}
@Override
public boolean isCredentialsNonExpired() {
	
	return true;
}
@Override
public boolean isEnabled() {
	
	return true;
}

	


}
