package com.example.answer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableFeignClients("com.example.answer.api")
@EnableDiscoveryClient
@FeignClient(name = "AnswerService")
public class AnswerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnswerApplication.class, args);
	}

}
