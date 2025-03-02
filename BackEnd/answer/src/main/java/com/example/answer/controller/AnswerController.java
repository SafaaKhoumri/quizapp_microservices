package com.example.answer.controller;

import com.example.answer.dto.AnswerDTO;
import com.example.answer.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    // ✅ Ajouter une nouvelle réponse
    @PostMapping("/addAnswer")
    public ResponseEntity<AnswerDTO> addAnswer(@RequestBody AnswerDTO answerDTO) {
        AnswerDTO savedAnswer = answerService.saveAnswer(answerDTO);
        return ResponseEntity.ok(savedAnswer);
    }

    // ✅ Récupérer une réponse par ID
    @GetMapping("/{id}")
    public Optional<AnswerDTO> getAnswer(@PathVariable Long id) {
        return answerService.getAnswerById(id);
    }

    // ✅ Récupérer toutes les réponses
    @GetMapping
    public List<AnswerDTO> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    // ✅ Supprimer une réponse par ID
    @DeleteMapping("/{id}")
    public void deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
    }

}
