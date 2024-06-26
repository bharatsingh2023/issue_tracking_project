package com.beas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserLoginApplication.class, args);
	}

}
