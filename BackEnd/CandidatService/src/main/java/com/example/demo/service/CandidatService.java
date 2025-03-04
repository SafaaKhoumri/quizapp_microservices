package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CandidatDTO;
import com.example.demo.model.Candidat;

public interface CandidatService {
    void createCandidat(Candidat candidat);
    List<Candidat> getAllCandidat();
    CandidatDTO addCandidat(CandidatDTO candidateDTO);
    CandidatDTO getCandidatById(Long id);
    List<CandidatDTO> getCandidatesByTestId(Long testId);
    public CandidatDTO getCandidatByEmail(String email);
}
