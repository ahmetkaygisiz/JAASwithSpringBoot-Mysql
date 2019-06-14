package com.akua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.akua.*")
public class SpringBootSecurityJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJdbcApplication.class, args);
	}
}
