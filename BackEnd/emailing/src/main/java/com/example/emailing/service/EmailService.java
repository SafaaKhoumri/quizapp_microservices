package com.example.emailing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.emailing.api.*;
import com.example.emailing.dto.candidatDTO;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private CandidatClient candidatClient;

     @Autowired
    private TestClient testClient;

    public void sendEmail(String to, String subject, String text) {
        logger.info("📤 Envoi de l'email à : {}", to);

        if (to == null || to.isEmpty()) {
            logger.error("❌ Erreur : L'adresse email est vide ou nulle.");
            throw new IllegalArgumentException("L'adresse email ne peut pas être vide.");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
        logger.info("✅ Email envoyé avec succès à : {}", to);
    }

    public void sendEmailWithTestLink(candidatDTO requestDTO) {
        if (requestDTO == null) {
            logger.error("❌ Erreur : L'objet requestDTO est nul.");
            throw new IllegalArgumentException("Le DTO du candidat ne peut pas être nul.");
        }

        if (requestDTO.getEmail() == null || requestDTO.getEmail().isEmpty()) {
            logger.error("❌ Erreur : L'email du candidat est nul ou vide.");
            throw new IllegalArgumentException("L'email du candidat ne peut pas être vide.");
        }

        if (requestDTO.getName() == null || requestDTO.getName().isEmpty()) {
            logger.warn("⚠️ Avertissement : Le nom du candidat est vide. Utilisation d'une valeur par défaut.");
            requestDTO.setName("Candidat");
        }

        if (requestDTO.getTestId() == null) {
            logger.error("❌ Erreur : L'ID du test est nul.");
            throw new IllegalArgumentException("L'ID du test ne peut pas être nul.");
        }

        String testLink = "http://localhost:3000/TakeTest/" + requestDTO.getTestId() + "?email="
                + requestDTO.getEmail();
        String body = "Bonjour " + requestDTO.getName() + ",\n\n" +
                "Vous êtes invité à passer le test. Cliquez ici : " + testLink + "\n\nBonne chance !";

        logger.info("📩 Envoi d'une invitation au test :");
        logger.info("   - Nom : {}", requestDTO.getName());
        logger.info("   - Email : {}", requestDTO.getEmail());
        logger.info("   - Test ID : {}", requestDTO.getTestId());
        logger.info("   - Lien du test : {}", testLink);

        sendEmail(requestDTO.getEmail(), "Invitation au test", body);
    }



     /*public void sendEmailaddcandidat(candidatDTO requestDTO) {
        if (requestDTO == null) {
            logger.error("❌ Erreur : L'objet requestDTO est nul.");
            throw new IllegalArgumentException("Le DTO du candidat ne peut pas être nul.");
        }

        if (requestDTO.getEmail() == null || requestDTO.getEmail().isEmpty()) {
            logger.error("❌ Erreur : L'email du candidat est nul ou vide.");
            throw new IllegalArgumentException("L'email du candidat ne peut pas être vide.");
        }

        if (requestDTO.getName() == null || requestDTO.getName().isEmpty()) {
            logger.warn("⚠️ Avertissement : Le nom du candidat est vide. Utilisation d'une valeur par défaut.");
            requestDTO.setName("Candidat");
        }

        if (requestDTO.getTestId() == null) {
            logger.error("❌ Erreur : L'ID du test est nul.");
            throw new IllegalArgumentException("L'ID du test ne peut pas être nul.");
        }

        logger.info("📝 Création du candidat dans le service CandidatService...");
        candidatDTO createdCandidat = candidatClient.addCandidate(requestDTO); 
        if (createdCandidat == null || createdCandidat.getId() == null) {
            logger.error("❌ Erreur : La création du candidat a échoué.");
            throw new RuntimeException("Échec de la création du candidat.");
        }
        try {
            logger.info("📡 Enregistrement du candidat dans le test via le microservice Test...");
            testClient.addCandidateToTest(requestDTO.getTestId(), createdCandidat.getId());
            logger.info("✅ Candidat ajouté au test avec succès !");
        } catch (Exception e) {
            logger.error("❌ Erreur lors de l'ajout du candidat au test : {}", e.getMessage());
            throw new RuntimeException("Impossible d'ajouter le candidat au test.");
        }

        logger.info("✅ Candidat créé avec succès. ID : {}", createdCandidat.getId());

        String testLink = "http://localhost:3000/TakeTest/" + requestDTO.getTestId() + "?email="
                + createdCandidat.getEmail();
        String body = "Bonjour " + createdCandidat.getName() + ",\n\n" +
                "Vous êtes invité à passer le test. Cliquez ici : " + testLink + "\n\nBonne chance !";

        logger.info("📩 Envoi de l'email d'invitation au test...");
        sendEmail(createdCandidat.getEmail(), "Invitation au test", body);
    }*/

   public void sendEmailaddcandidat(candidatDTO requestDTO) {
    if (requestDTO == null) {
        throw new IllegalArgumentException("Le DTO du candidat ne peut pas être nul.");
    }
    if (requestDTO.getEmail() == null || requestDTO.getEmail().isEmpty()) {
        throw new IllegalArgumentException("L'email du candidat ne peut pas être vide.");
    }
    if (requestDTO.getTestId() == null) {
        throw new IllegalArgumentException("L'ID du test ne peut pas être nul.");
    }

    logger.info("📝 Création du candidat dans le service CandidatService...");
    candidatDTO createdCandidat = candidatClient.addCandidate(requestDTO);

    if (createdCandidat == null || createdCandidat.getId() == null) {
        throw new RuntimeException("Échec de la création du candidat.");
    }

    logger.info("✅ Candidat créé avec succès. ID : {}", createdCandidat.getId());

    // 🔥 Envoi de l’email
    String testLink = "http://localhost:3000/TakeTest/" + requestDTO.getTestId() + "?email="
            + createdCandidat.getEmail();
    String body = "Bonjour " + createdCandidat.getName() + ",\n\n" +
            "Vous êtes invité à passer le test. Cliquez ici : " + testLink + "\n\nBonne chance !";

    logger.info("📩 Envoi de l'email d'invitation au test...");
    sendEmail(createdCandidat.getEmail(), "Invitation au test", body);
}
}
