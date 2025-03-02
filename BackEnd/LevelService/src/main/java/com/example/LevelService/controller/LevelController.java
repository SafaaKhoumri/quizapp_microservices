package com.example.LevelService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LevelService.dto.LevelDTO;
import com.example.LevelService.model.Level;
import com.example.LevelService.service.LevelService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("level")
public class LevelController {
    @Autowired
    LevelService levelService;

    @GetMapping
    public List<Level> getLevels() {
        return levelService.geLevels();
    }

    @GetMapping("/{id}")
    public LevelDTO getLevelById(@PathVariable Long id) {
        return levelService.getLevelById(id);
    }
       
    @GetMapping("/theme/{themeId}")
    public List<LevelDTO> getLevelsByThemeId(@PathVariable Long themeId) {
        return levelService.getLevelsByThemeId(themeId);
    }
    
}
