package com.example.emailing.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.emailing.dto.TestDTO;

@FeignClient(name = "Test-service")
public interface TestClient {

    @GetMapping("/tests/{id}")
    TestDTO getTestById(@PathVariable Long id);

    @PostMapping("/tests/{testId}/addCandidate/{candidateId}")
    void addCandidateToTest(@PathVariable("testId") Long testId, @PathVariable("candidateId") Long candidateId);
    
}
