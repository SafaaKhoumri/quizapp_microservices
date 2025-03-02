package com.example.ThemeService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ThemeService.model.Theme;

import java.util.List;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
    // ✅ `findAll` est déjà inclus par défaut grâce à JpaRepository
    List<Theme> findAll(); // Permet d'obtenir tous les thèmes
}
