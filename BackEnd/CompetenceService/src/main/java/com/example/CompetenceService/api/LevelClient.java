package com.example.CompetenceService.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.CompetenceService.dto.LevelDTO;

@FeignClient(name = "LevelService")
public interface LevelClient {
    @GetMapping("/level/{id}")
    LevelDTO getLevelById(@PathVariable Long id);
}
