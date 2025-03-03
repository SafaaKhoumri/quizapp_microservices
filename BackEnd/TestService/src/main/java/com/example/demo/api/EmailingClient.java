package com.example.demo.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.CandidatDTO;

@FeignClient(name = "emailing", url = "http://localhost:8080")
public interface EmailingClient {
    
    @PostMapping("/api/email/sendTestLink")
    public ResponseEntity<String> sendTestLink(@RequestBody CandidatDTO requestDTO);

}
