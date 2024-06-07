package com.beas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AddProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddProjectApplication.class, args);
	}

}
