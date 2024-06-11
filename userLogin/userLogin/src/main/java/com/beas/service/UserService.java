package com.beas.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.beas.dto.UserDto;
import com.beas.entities.User;
import com.beas.entities.VerificationToken;
import com.beas.jwtutil.JwtUtil;
import com.beas.repository.UserRepo;
import com.beas.repository.VerificationTokenRepository;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private VerificationTokenRepository tokenRepo;
	@Autowired
	private EmailService emailService;
	@Autowired
	private JwtUtil jwtUtil;

	public User signUp(UserDto userDto) throws InterruptedException {

		User saved =null;
		try {
			 if(userRepo.findByUsername(userDto.getUsername())==null) {		
			emailService.sendVerificationEmail(userDto.getUsername(), userDto.getUsername());
			User user = new User();
			user.setName(userDto.getName());
			user.setUsername(userDto.getUsername());
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
			user.setCreationDate(String.valueOf(new Date()));
			user.setVerified(false);
			saved = userRepo.save(user);
			 }else {
				 emailService.sendVerificationEmail(userDto.getUsername(), userDto.getUsername());
				 User user = userRepo.findByUsername(userDto.getUsername()).get();
				 user.setName(userDto.getName());
					user.setUsername(userDto.getUsername());
					user.setPassword(passwordEncoder.encode(userDto.getPassword()));
					user.setCreationDate(String.valueOf(new Date()));
					user.setVerified(false);
					userRepo.save(user);
			 }
		} catch (Exception e) {
			System.out.println("Email sending error " + e.getMessage());
		}

		return saved;

	}

	public List<User> getUser() {
		return userRepo.findAll();
	}

	public Optional<User> findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	public VerificationToken saveVerificationToken(VerificationToken verificationToken) {

		tokenRepo.save(verificationToken);

		emailService.sendVerificationEmail(verificationToken.getUser().getUsername(), verificationToken.getToken());

		return verificationToken;
	}

	public boolean validateToken(String username) {
		User userData = null;
		try {
			User user = userRepo.findByUsername(username).get();
			user.setVerified(true);
			userData = userRepo.save(user);
		} catch (Exception e) {
			System.out.println("error in validating " + e.getMessage());
		}
		return userData != null ? true : false;
	}


	public String getKey(String username) {
		return jwtUtil.generateToken(username);
		
	}

}
