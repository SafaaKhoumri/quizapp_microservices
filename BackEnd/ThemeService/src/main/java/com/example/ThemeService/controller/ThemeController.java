package com.example.ThemeService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ThemeService.dto.ThemeDTO;
import com.example.ThemeService.service.ThemeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/theme")
public class ThemeController {
    @Autowired
    private ThemeService themeService;

    // ✅ Récupérer un thème par ID
    @GetMapping("/{id}")
    public ThemeDTO getThemeById(@PathVariable Long id) {
        System.out.println("Requête reçue pour le thème ID: " + id);
        return themeService.getThemeById(id);
    }

    // ✅ Récupérer **tous** les thèmes
    @GetMapping("/themes")
    public List<ThemeDTO> getAllThemes() {
        System.out.println("Requête reçue pour récupérer tous les thèmes.");
        return themeService.getAllThemes();
    }
}
