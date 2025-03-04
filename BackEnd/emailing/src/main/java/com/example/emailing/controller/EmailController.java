package com.example.emailing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.example.emailing.dto.candidatDTO;
import com.example.emailing.service.EmailService;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendTestLink")
    public ResponseEntity<String> sendTestLink(@RequestBody candidatDTO requestDTO) {
        System.out.println("📥 Reçu : " + requestDTO);

        if (requestDTO.getName() == null || requestDTO.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur : Le nom du candidat est vide ou nul.");
        }
        if (requestDTO.getEmail() == null || requestDTO.getEmail().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur : L'email du candidat est vide ou nul.");
        }
        if (requestDTO.getTestId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur : L'ID du test est nul.");
        }
        if (requestDTO.getTestName() == null || requestDTO.getTestName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur : Le nom du test est vide ou nul.");
        }

        try {
            emailService.sendEmailWithTestLink(requestDTO);
            return ResponseEntity.ok("Email envoyé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'envoi de l'email : " + e.getMessage());
        }
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("EmailService fonctionne correctement");
    }
}