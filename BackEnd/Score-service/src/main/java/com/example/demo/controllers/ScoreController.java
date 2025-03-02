package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ScoreDTO;
import com.example.demo.dto.TestDTO;
import com.example.demo.model.Score;
import com.example.demo.service.ScoreService;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @PostMapping("/saveScore")
    public ResponseEntity<Score> saveScore(@RequestBody ScoreDTO scoreDTO) {
        Score score = scoreService.saveScore(
            scoreDTO.getTestId(),
            scoreDTO.getCandidatId(),
            scoreDTO.getCorrectAnswers(),
            scoreDTO.getTotalQuestions()
        );
        
        return ResponseEntity.ok(score);
    }
    

    @GetMapping("/test/{testId}/candidat/{candidatId}")
    public ResponseEntity<Score> getScoreByTestAndCandidat(@PathVariable Long testId, @PathVariable Long candidatId) {
        Optional<Score> scoreOpt = scoreService.getScoreByTestAndCandidat(testId, candidatId);
        if (scoreOpt.isPresent()) {
            return ResponseEntity.ok(scoreOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    
}