package com.beas.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beas.dto.UserDto;
import com.beas.entities.User;
import com.beas.service.EmailService;
import com.beas.service.UserService;

import jakarta.validation.Valid;

@RestController
@Valid
public class UserLoginController {
	Random random = new Random(1000);
	@Autowired
	private EmailService emailservice;
	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<?> SignUp(@RequestBody UserDto userDto) throws InterruptedException {
		userService.signUp(userDto);
		return ResponseEntity.ok("User Signup");
	}

	@GetMapping("/user")
	public List<User> getUser() {
		System.out.println("getting users");
		return userService.getUser();
	}
	
	@PostMapping("/verify")
    public String validateToken(@RequestParam("token") String token) {
        if (userService.validateToken(token)) {
            return "User verified successfully!";
        } else {
            return "Invalid or expired token";
        }
	}

	@PostMapping("/send-otp")
	public String sendOTP(@RequestParam("username") String username) {
		System.out.println("Email" + username);
		int otp = random.nextInt(999999);
		System.out.println("OTP" + "otp");
		return "";
	}
}