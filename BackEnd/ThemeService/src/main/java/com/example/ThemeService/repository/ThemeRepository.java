package com.example.ThemeService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ThemeService.model.Theme;


@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

}
