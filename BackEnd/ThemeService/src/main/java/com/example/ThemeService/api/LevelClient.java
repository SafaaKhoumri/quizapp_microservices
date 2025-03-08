package com.example.ThemeService.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ThemeService.dto.LevelDTO;

@FeignClient(name = "LevelService")
public interface LevelClient {
    @GetMapping("/level/theme/{themeId}")
    LevelDTO getLevelByThemeId(@PathVariable Long themeId);
}