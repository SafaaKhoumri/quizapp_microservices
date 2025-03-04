package com.example.QuestionService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.QuestionService.dto.CompetencyDTO;

@FeignClient(name = "CompetencyService", url = "http://localhost:8089/competences")
public interface CompetencyClient {
    @GetMapping("/{id}")
    CompetencyDTO getCompetencyById(@PathVariable("id") Long id);
}
