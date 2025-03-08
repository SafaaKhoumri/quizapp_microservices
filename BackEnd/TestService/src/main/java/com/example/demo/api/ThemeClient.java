package com.example.demo.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.ThemeDTO;

@FeignClient(name = "ThemeService")
public interface ThemeClient {

    @GetMapping("/theme/{id}")
    ThemeDTO getThemeById(@PathVariable Long id);
    
}
