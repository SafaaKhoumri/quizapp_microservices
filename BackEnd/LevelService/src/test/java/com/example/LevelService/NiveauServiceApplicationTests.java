package com.example.LevelService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootTest
@EnableFeignClients(basePackages = "com.example.LevelService.api")
class LevelServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
