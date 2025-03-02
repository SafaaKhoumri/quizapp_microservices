package com.example.LevelService.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.LevelService.model.Level;

@Repository
public interface LevelRepository extends CrudRepository<Level, Long>{
    List<Level> findByThemeId(Long themeId);
}
