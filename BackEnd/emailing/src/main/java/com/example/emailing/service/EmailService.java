package com.example.emailing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.emailing.dto.candidatDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String to, String subject, String text) {
        logger.info("Envoi de l'email à : {}", to);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
        logger.info("Email envoyé à : {}", to);
    }

    public void sendEmailWithTestLink(candidatDTO requestDTO) {
        String testLink = "http://localhost:3000/TakeTest/" + requestDTO.getId() + "?email="
                + requestDTO.getEmail();
        String body = "Bonjour " + requestDTO.getName() + ",\n\n" +
                "Vous êtes invité à passer le test. Cliquez ici : " + testLink + "\n\nBonne chance !";
        sendEmail(requestDTO.getEmail(), "Invitation au test", body);
    }
}