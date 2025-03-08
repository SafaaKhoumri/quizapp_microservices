package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.demo.api")
@EnableDiscoveryClient
@FeignClient(name = "CandidatService")
public class CandidatServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CandidatServiceApplication.class, args);
	}

}
