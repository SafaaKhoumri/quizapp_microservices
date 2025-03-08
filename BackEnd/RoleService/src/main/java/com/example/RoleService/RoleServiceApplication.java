package com.example.RoleService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.RoleService.api")
@EnableDiscoveryClient
@FeignClient(name = "RoleService")
public class RoleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoleServiceApplication.class, args);
	}

}
