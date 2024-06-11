package com.beas.service;

import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.beas.entities.User;
import com.beas.repository.UserRepo;

import jakarta.servlet.http.HttpServletRequest;
@Service
public class UserDetailServiceimpl implements UserDetailsService{
	@Autowired
	private UserRepo userRepo; 

	@Autowired
	HttpServletRequest httpServletRequest;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		if(username==null) {
			throw new UsernameNotFoundException("username not found");
		}
		User user= userRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("user not found:"+username));
		return user;
	}

}
