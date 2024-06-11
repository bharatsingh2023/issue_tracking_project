package com.beas.securityconfig;

import java.security.Key;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.beas.service.CustomFilter;
import com.beas.service.UserDetailServiceimpl;

@EnableWebSecurity
@Configuration
public class Securityconfig {
	
	
	
@Autowired
private UserDetailServiceimpl userDetailService;
	@Bean
	SecurityFilterChain securityfilterchain(HttpSecurity httpsecurity) throws Exception {
		httpsecurity.csrf(c->c.disable())
				.authorizeHttpRequests(
						r ->r.requestMatchers("/signup","/signin","/user","/verify").permitAll()
						.anyRequest().authenticated()).formLogin(form->form.loginPage("/login").defaultSuccessUrl("/postlogin"));
				
		
		return httpsecurity.build();
	}
	
	/*@Bean
	public Key getKey() {
		
		byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
        return new SecretKeySpec(decodedKey, ALGORITHM);
	}*/
	 /*@Bean
	    DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
	        daoAuthenticationProvider.setUserDetailsService(userDetailService);
	        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

	        return daoAuthenticationProvider;
	    }
	
		@Bean
		AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
			return builder.getAuthenticationManager();
		}*/
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
