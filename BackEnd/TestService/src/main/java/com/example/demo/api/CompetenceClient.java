package com.example.demo.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.example.demo.dto.CompetenceDTO;

@FeignClient(name = "CompetenceService", url = "http://localhost:8089")
public interface CompetenceClient {

    @PostMapping("/competences")
    CompetenceDTO addCompetence(@RequestBody CompetenceDTO competenceDTO);

    @GetMapping("/competences/{id}")
    CompetenceDTO getCompetenceById(@PathVariable Long id);

    
}
