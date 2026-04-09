package com.example.bankproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BankprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankprojectApplication.class, args);
	}

}
