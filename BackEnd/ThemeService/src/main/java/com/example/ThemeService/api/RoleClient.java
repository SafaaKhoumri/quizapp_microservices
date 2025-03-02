package com.example.ThemeService.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.ThemeService.dto.RoleDTO;

@FeignClient(name = "RoleService", url = "http://localhost:8082")
public interface RoleClient {
    @GetMapping("/role/{themeId}")
    RoleDTO getRoleByThemeId(@PathVariable Long themeId);
}
