package com.beas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private UserDetailServiceimpl userDetailServiceimpl; 
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		UserDetails userDetails = userDetailServiceimpl.loadUserByUsername(authentication.getName());
		
		
		if(passwordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
			
		UsernamePasswordAuthenticationToken authenticationToken =	new UsernamePasswordAuthenticationToken(
				userDetails.getUsername(), userDetails.getPassword(),userDetails.getAuthorities());
		
		return authenticationToken;
		}	
		throw new BadCredentialsException("incorrect password");
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
