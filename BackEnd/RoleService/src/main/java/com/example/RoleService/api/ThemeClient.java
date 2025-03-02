package com.example.RoleService.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.RoleService.dto.ThemeDTO;

@FeignClient(name = "ThemeService", url = "http://localhost:8085")
public interface ThemeClient {
    @GetMapping("/theme/{id}")
    ThemeDTO getThemeById(@PathVariable Long id);
}
