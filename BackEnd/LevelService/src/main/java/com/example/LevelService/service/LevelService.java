package com.example.LevelService.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LevelService.api.ThemeClient;
import com.example.LevelService.dto.LevelDTO;
import com.example.LevelService.dto.ThemeDTO;
import com.example.LevelService.model.Level;
import com.example.LevelService.repository.LevelRepository;

@Service
public class LevelService {
    @Autowired
    LevelRepository levelRepository;
    @Autowired
    ThemeClient themeClient;

    public LevelDTO entityToDTO(Level level) {
        LevelDTO dto = new LevelDTO();
        dto.setId(level.getId());
        dto.setName(level.getName());

        ThemeDTO theme = themeClient.getThemeById(level.getThemeId());
        dto.setTheme(theme);

        return dto;
    }

    public List<Level> geLevels(){
        return (List<Level>) levelRepository.findAll();
    } 

    public LevelDTO getLevelById(Long id){
        return this.entityToDTO(levelRepository.findById(id).get());
    }

     public List<LevelDTO> getLevelsByThemeId(Long themeId) {
        List<Level> levels = levelRepository.findByThemeId(themeId);
        return levels.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

}
