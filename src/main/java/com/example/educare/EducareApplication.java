package com.example.educare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@EntityScan("com.user")
@EnableJpaRepositories("com.repo")
@ComponentScan(basePackages = "com.controller,"+"com.user,"+"com.services")
@EnableAsync

@SpringBootApplication
public class EducareApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducareApplication.class, args);
	}

}
