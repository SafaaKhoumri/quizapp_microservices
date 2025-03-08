package com.example.emailing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.emailing.api")
@ComponentScan(basePackages = "com.example.emailing")
@EnableDiscoveryClient
@FeignClient(name = "emailing")
public class EmailingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailingApplication.class, args);
	}

}
