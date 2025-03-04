package com.example.QuestionService.service;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.QuestionService.client.CompetencyClient;
import com.example.QuestionService.dto.CompetencyDTO;
import com.example.QuestionService.dto.QuestionAnswerDTO;
import com.example.QuestionService.dto.QuestionDTO;
import com.example.QuestionService.model.Question;
import com.example.QuestionService.repository.QuestionRepository;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;


    @Autowired
    CompetencyClient competencyClient; // Feign Client pour récupérer la compétence

    public QuestionDTO entityToDTO(Question question) {
        if (question == null) {
            return null;
        }

        QuestionDTO dto = new QuestionDTO();
        dto.setId(question.getId());
        dto.setQuestionText(question.getQuestionText());

        return dto;
    }

    public List<QuestionAnswerDTO> getQuestionsAndAnswersByTestId(Long testId) {
        List<Question> questions = questionRepository.findQuestionsByTestId(testId);
    
        return questions.stream()
            .map(question -> new QuestionAnswerDTO(
                question.getId(),
                question.getQuestionText(),
                question.getAnswerChoices()))
            .collect(Collectors.toList());
    }

    public List<QuestionDTO> findQuestionsByCompetencyIds(List<Long> competencyIds) {
        List<Question> questions = questionRepository.findQuestionsByCompetencyIds(competencyIds);

        if (questions.isEmpty()) {
            System.out.println("❌ Aucune question trouvée pour les compétences : " + competencyIds);
        }

        return questions.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    public QuestionDTO getQuestionById(Long id) {
        return questionRepository.findById(id)
                .map(this::entityToDTO)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));
    }

    public QuestionAnswerDTO getQuestionWithAnswers(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));

        // 🔥 Récupérer la compétence depuis CompetencyService via Feign Client
        CompetencyDTO competency = competencyClient.getCompetencyById(question.getCompetencyId());

        return new QuestionAnswerDTO(
                question.getId(),
                question.getQuestionText(),
                question.getAnswerChoices(),
                competency // Ajoute la compétence au DTO
        );
    }
}
