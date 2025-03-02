package com.example.emailing.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.emailing.dto.candidatDTO;

@FeignClient(name = "CandidatService", url = "http://localhost:8071")
public interface CandidatClient {

    @GetMapping("/candidat/{id}")
    candidatDTO getCandidateById(@PathVariable Long id);

    @PostMapping("/candidat")
    candidatDTO addCandidate(@RequestBody candidatDTO CandidatDTO);

}
