package com.example.demo.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.CandidatDTO;

@FeignClient(name = "CandidatService", url = "http://localhost:8071")
public interface CandidatClient {
    @GetMapping("/candidat/{id}")
    CandidatDTO getCandidatById(@PathVariable Long id);
}
