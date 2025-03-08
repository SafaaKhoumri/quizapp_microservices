package com.example.LevelService.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.LevelService.dto.ThemeDTO;

@FeignClient(name = "ThemeService")
public interface ThemeClient {
    @GetMapping("/theme/{id}")
    ThemeDTO getThemeById(@PathVariable Long id);
}