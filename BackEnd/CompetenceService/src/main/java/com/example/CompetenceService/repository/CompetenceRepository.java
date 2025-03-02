package com.example.CompetenceService.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.CompetenceService.model.Competence;

@Repository
public interface CompetenceRepository extends CrudRepository<Competence, Long> {
    List<Competence> findByRoleIdAndLevelId(Long roleId, Long levelId);

}
