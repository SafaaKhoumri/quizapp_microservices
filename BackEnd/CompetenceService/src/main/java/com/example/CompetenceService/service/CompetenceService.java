package com.example.CompetenceService.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.CompetenceService.dto.CompetenceDTO;
import com.example.CompetenceService.model.Competence;
import com.example.CompetenceService.repository.CompetenceRepository;

@Service
public class CompetenceService {
    @Autowired
    CompetenceRepository competenceRepository;


    public List<CompetenceDTO> getCompetencesByRoleAndLevel(Long roleId, Long levelId) {
        List<Competence> competences = competenceRepository.findByRoleIdAndLevelId(roleId, levelId);
        return competences.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private CompetenceDTO convertToDTO(Competence competence) {
        CompetenceDTO dto = new CompetenceDTO();
        dto.setId(competence.getId());
        dto.setName(competence.getName());

        dto.setRoleId(competence.getRoleId());// setRole(roleClient.getRoleById(competence.getRoleId()));
        dto.setLevelId(competence.getLevelId()); //levelClient.getLevelById(competence.getLevelId())

        return dto;
    }
    public CompetenceDTO addCompetence(CompetenceDTO competenceDTO) {
        Competence competence = new Competence();
        competence.setName(competenceDTO.getName());
        competence.setRoleId(competenceDTO.getRoleId());
        competence.setLevelId(competenceDTO.getLevelId());
    
        Competence savedCompetence = competenceRepository.save(competence);
        return new CompetenceDTO(savedCompetence.getId(), savedCompetence.getName(), savedCompetence.getRoleId(), savedCompetence.getLevelId());
    }

    public CompetenceDTO getCompetenceById(Long id) {
        Competence competence = competenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compétence avec l'ID " + id + " non trouvée"));
    
        return convertToDTO(competence);
    }
      
}


