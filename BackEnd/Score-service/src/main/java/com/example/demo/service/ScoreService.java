package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.api.CandidatClient;
import com.example.demo.api.TestClient;
import com.example.demo.dto.CandidatDTO;
import com.example.demo.dto.ScoreDTO;
import com.example.demo.model.Score;
import com.example.demo.repositories.ScoreRepository;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private CandidatClient candidatClient;

    @Autowired
    private TestClient testClient;

  
    public List<Score> getScoresByTest(Long testId) {
        return scoreRepository.findByTestId(testId);
    }

    public Optional<Score> getScoreByTestAndCandidat(Long testId, Long candidatId) {
        return scoreRepository.findByTestIdAndCandidatId(testId, candidatId);
    }

    public Score saveScore(Long testId, Long candidatId, int correctAnswers, int totalQuestions) {
        if (testClient.getTestById(testId) != null && candidatClient.getCandidatById(candidatId) != null) {
            Score score = new Score(candidatId, testId, correctAnswers, totalQuestions);
            return scoreRepository.save(score);
        }
        throw new RuntimeException("Le Test ou le Candidat n'existe pas !");
    }

    public String getNomCandidatById(Long candidatId) {
        CandidatDTO candidat = candidatClient.getCandidatById(candidatId);
        return (candidat != null && candidat.getName() != null) ? candidat.getName() : "Candidat inconnu";
    }

    public List<ScoreDTO> getScoresWithCandidatsByTest(Long selectedTest) {
        List<Score> scores = scoreRepository.findByTestId(selectedTest);
        
        return scores.stream().map(score -> {
            // Utilisation de la méthode getNomCandidatById()
            String candidatName = getNomCandidatById(score.getCandidatId());

            return new ScoreDTO(
                score.getCandidatId(),
                score.getTestId(),
                candidatName,
                score.getCorrectAnswers(),
                score.getTotalQuestions()
            );
        }).collect(Collectors.toList());
    }


    
    
}
