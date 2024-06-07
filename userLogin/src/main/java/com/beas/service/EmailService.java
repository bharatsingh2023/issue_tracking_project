package com.beas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	 @Autowired
	    private JavaMailSender mailSender;

	    public void sendVerificationEmail(String to, String token) {
	        String subject = "Verify your email";
	        String verificationUrl = "http://localhost:8080/verify?token=" + token;

	        String message = "Click the link to verify your email: " + verificationUrl;

	        SimpleMailMessage email = new SimpleMailMessage();
	        email.setTo(to);
	        email.setSubject(subject);
	        email.setText(message);

	        mailSender.send(email);
	    }
}
