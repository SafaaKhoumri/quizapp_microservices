package com.example.QuestionService.client;

import com.example.QuestionService.dto.TestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Test-service", url = "http://localhost:8070") // Ajustez le port si nécessaire
public interface TestClient {

    @GetMapping("/tests/{id}")
    TestDTO getTestById(@PathVariable Long id);
}
