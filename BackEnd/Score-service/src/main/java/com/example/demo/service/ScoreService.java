package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.api.CandidatClient;
import com.example.demo.api.TestClient;
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
    
}
