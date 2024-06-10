package com.beas.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beas.dto.UserDto;
import com.beas.entities.User;
import com.beas.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@Valid
@CrossOrigin(origins = "*")
public class UserLoginController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<?> SignUp(@RequestBody UserDto userDto) throws InterruptedException {
		userService.signUp(userDto);
		return ResponseEntity.ok("email sent");
	}


	@GetMapping("/verify")
	public void verifyUser(@RequestParam String username, HttpServletResponse response) throws IOException {
		if (userService.validateToken(username)) {
			response.sendRedirect("http://localhost:3000/login?verified=true");
		} else {
			response.sendRedirect("http://localhost:3000/login?verified=false");
		}
	}
	
	
	@GetMapping("/user")
	public List<User> getUser() {
		System.out.println("getting users");
		return userService.getUser();
	}

}