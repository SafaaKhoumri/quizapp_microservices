package com.example.LevelService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.LevelService.api")
public class LevelServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LevelServiceApplication.class, args);
	}

}
