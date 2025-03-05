package com.example.answer.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.answer.api.CandidatClient;
import com.example.answer.api.QuestionClient;
import com.example.answer.dto.AnswerDTO;
import com.example.answer.dto.CandidatDTO;
import com.example.answer.dto.QuestionDTO;
import com.example.answer.model.Answer;
import com.example.answer.repositories.AnswerRepository;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionClient questionClient;

    @Autowired
    private CandidatClient candidatClient;

    // Convertir Answer en AnswerDTO avec détails du candidat et de la question
    public AnswerDTO toDTO(Answer answer) {
        QuestionDTO questionDTO = questionClient.getQuestionById(answer.getQuestionId());
        CandidatDTO candidatDTO = candidatClient.getCandidatById(answer.getCandidatId());

        return new AnswerDTO(
                answer.getId(),
                answer.getTexteReponse(),
                answer.isEstCorrecte(),
                candidatDTO.getId(),
                questionDTO.getId()
        );
    }

    // Convertir AnswerDTO en Answer
    public Answer toEntity(AnswerDTO answerDTO) {
        return new Answer(
                answerDTO.getId(),
                answerDTO.getTexteReponse(),
                answerDTO.isEstCorrecte(),
                answerDTO.getCandidatId(),
                answerDTO.getQuestionId()
        );
    }

    // Sauvegarder une réponse
    public AnswerDTO saveAnswer(AnswerDTO answerDTO) {
        Answer answer = toEntity(answerDTO);
        answer = answerRepository.save(answer);
        return toDTO(answer);
    }

    
    // Récupérer une réponse par ID
    public Optional<AnswerDTO> getAnswerById(Long id) {
        Optional<Answer> answer = answerRepository.findById(id);
        return answer.map(this::toDTO);
    }

    // Récupérer toutes les réponses
    public List<AnswerDTO> getAllAnswers() {
        return answerRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Supprimer une réponse
    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }

    public List<AnswerDTO> saveMultipleAnswers(List<AnswerDTO> answerDTOs, String candidatEmail) {
        // Fetch candidatId using the email
        System.out.println("🔍 Fetching candidat by email: " + candidatEmail);
        CandidatDTO candidat = candidatClient.getCandidatByEmail(candidatEmail);
        Long candidatId = candidat.getId();
    
        // Attach candidatId to each answer
        List<Answer> answers = answerDTOs.stream()
            .map(dto -> new Answer(null, dto.getTexteReponse(), dto.isEstCorrecte(), candidatId, dto.getQuestionId()))
            .collect(Collectors.toList());
    
        List<Answer> savedAnswers = answerRepository.saveAll(answers);
        return savedAnswers.stream().map(this::toDTO).collect(Collectors.toList());
    }
    
    
}
