package com.example.answer.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.answer.dto.ScoreDTO;

@FeignClient(name = "ScoreService", url = "http://localhost:8086") // Change URL if needed
public interface ScoreClient {
    
    @PostMapping("/scores")
    void saveScore(@RequestBody ScoreDTO scoreDTO);
}