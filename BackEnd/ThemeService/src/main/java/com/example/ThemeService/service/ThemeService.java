package com.example.ThemeService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ThemeService.dto.ThemeDTO;
import com.example.ThemeService.model.Theme;
import com.example.ThemeService.repository.ThemeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ThemeService {
    @Autowired
    private ThemeRepository themeRepository;

    // ✅ Convertir une entité Theme en DTO
    public ThemeDTO entityToDTO(Theme theme) {
        if (theme == null) {
            return null;
        }
        ThemeDTO dto = new ThemeDTO();
        dto.setId(theme.getId());
        dto.setName(theme.getName());
        return dto;
    }

    // ✅ Récupérer un thème par ID
    public ThemeDTO getThemeById(Long id) {
        Optional<Theme> themeOpt = themeRepository.findById(id);
        if (themeOpt.isPresent()) {
            return entityToDTO(themeOpt.get());
        } else {
            throw new RuntimeException("Thème non trouvé avec ID: " + id);
        }
    }

    // ✅ Récupérer tous les thèmes
    public List<ThemeDTO> getAllThemes() {
        List<Theme> themes = themeRepository.findAll();
        return themes.stream().map(this::entityToDTO).collect(Collectors.toList());
    }
}
