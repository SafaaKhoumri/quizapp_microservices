package com.example.demo.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.TestDTO;

@FeignClient(name = "Test-service")
public interface TestClient {
    
    @GetMapping("/tests/{id}")
    TestDTO getTestById(@PathVariable Long id);
}