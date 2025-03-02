package com.example.CompetenceService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.CompetenceService.api")

public class CompetenceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompetenceServiceApplication.class, args);
	}

}
