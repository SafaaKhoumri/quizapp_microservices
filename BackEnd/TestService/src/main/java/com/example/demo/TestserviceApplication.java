package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.demo.api")
@ComponentScan(basePackages = "com.example.demo")
@EnableDiscoveryClient
@FeignClient(name = "Test-service")
public class TestserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestserviceApplication.class, args);
	}

}
