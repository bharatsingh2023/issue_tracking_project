package com.beas.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.beas.dto.UserDto;
import com.beas.entities.User;
import com.beas.entities.VerificationToken;
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

	public User signUp(UserDto userDto) throws InterruptedException {

		User saved =null;
		try {
			emailService.sendVerificationEmail(userDto.getUsername(), userDto.getUsername());
			User user = new User();
			user.setName(userDto.getName());
			user.setUsername(userDto.getUsername());
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
			user.setCreationDate(String.valueOf(new Date()));
			user.setVerified(false);
			saved = userRepo.save(user);
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
		// Token expires in 15 minutes

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

	public static Thread findAThread() {

		ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
		int noThreads = currentGroup.activeCount();
		Thread[] lstThreads = new Thread[noThreads];
		// Get the threads into the array
		currentGroup.enumerate(lstThreads);

		for (Thread thread : lstThreads) {
			if (thread != null && thread.getName().equals("eee")) {
				return thread;
			}
		}
		return null;
	}

}
