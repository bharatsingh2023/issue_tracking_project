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

Random random = new Random(1000);

@Transactional(propagation = Propagation.REQUIRES_NEW)
public User signUp(UserDto userDto) throws InterruptedException {
	 
	User user=new User();
	user.setName(userDto.getName());
	user.setUsername(userDto.getUsername());
	user.setPassword(passwordEncoder.encode(userDto.getPassword()));
	user.setCreationDate(String.valueOf(new Date()));
	user.setVerified(false);
		
	int otp = random.nextInt(999999);
	//saveVerificationToken(String.valueOf(otp),  user);
	
	
	VerificationToken  verificationToken=new  VerificationToken();
	 verificationToken.setToken(String.valueOf(otp));
    verificationToken.setUser(user);
    verificationToken.setExpiryTime(LocalDateTime.now().plusMinutes(20));
	
	Runnable runnable=new Runnable() {
		
		@Override
		public void run() {

			saveVerificationToken(verificationToken);
			//userRepo.save(user);
		}
	};
	
	Thread thread=new Thread(runnable);
	thread.start();
			
	Thread currentThread = Thread.currentThread();
	currentThread.setName("eee");
	currentThread.join(60*1000);
	
	tokenRepo.delete(verificationToken);
	userRepo.delete(user);
	
	return user;
	
}

public List<User> getUser(){
	return userRepo.findAll();
}


public Optional <User> findByUsername(String username){
	return userRepo.findByUsername(username);
}

public VerificationToken saveVerificationToken(VerificationToken verificationToken) {
	   // Token expires in 15 minutes

     tokenRepo.save(verificationToken);

     emailService.sendVerificationEmail(verificationToken.getUser().getUsername(), verificationToken.getToken());
     
     return verificationToken;
}

public boolean validateToken(String token) {
	
	UserService usimpl=null;
	Thread thread= usimpl.findAThread();
	System.err.println(thread.getName());
	
	thread.stop();;
	
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
