package com.beas.issuelist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IssueListApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssueListApplication.class, args);
	}

}
