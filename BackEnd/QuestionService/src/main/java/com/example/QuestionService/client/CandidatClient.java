package com.example.QuestionService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Candidat-service", url = "http://localhost:8080") // Adjust URL
public interface CandidatClient {
    @GetMapping("/candidats/{id}/testId")
    Long getTestIdByCandidatId(@PathVariable Long id);
}