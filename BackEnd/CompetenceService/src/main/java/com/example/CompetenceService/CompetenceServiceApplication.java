package com.example.CompetenceService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.CompetenceService.api")
@EnableDiscoveryClient
@FeignClient(name = "CompetenceService")
public class CompetenceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompetenceServiceApplication.class, args);
	}

}
