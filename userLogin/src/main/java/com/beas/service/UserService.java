package com.beas.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
public User signUp(UserDto userDto) {
	 
	User user=new User();
	user.setName(userDto.getName());
	user.setUsername(userDto.getUsername());
	user.setPassword(passwordEncoder.encode(userDto.getPassword()));
	user.setCreationDate(String.valueOf(new Date()));
	user.setVerified(false);
	userRepo.save(user);
	return user;
	
}

public List<User> getUser(){
	return userRepo.findAll();
}


public Optional <User> findByUsername(String username){
	return userRepo.findByUsername(username);
}

public void saveVerificationToken(String token, User user) {
	 VerificationToken  verificationToken=new  VerificationToken();
	 verificationToken.setToken(token);
     verificationToken.setUser(user);
     verificationToken.setExpiryTime(LocalDateTime.now().plusMinutes(20));  // Token expires in 15 minutes

     tokenRepo.save(verificationToken);

     emailService.sendVerificationEmail(user.getUsername(), token);
}

public boolean validateToken(String token) {
	VerificationToken verificationToken = tokenRepo.findByToken(token);
	if (verificationToken == null || verificationToken.getExpiryTime().isBefore(LocalDateTime.now())) {
        return false;
    }
	User user = verificationToken.getUser();
    user.setVerified(true);
    userRepo.save(user);

    tokenRepo.delete(verificationToken);

    return true;
}



}
