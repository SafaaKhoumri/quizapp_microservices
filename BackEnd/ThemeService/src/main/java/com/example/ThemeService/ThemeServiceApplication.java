package com.example.ThemeService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.ThemeService.api")
public class ThemeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThemeServiceApplication.class, args);
	}

}
