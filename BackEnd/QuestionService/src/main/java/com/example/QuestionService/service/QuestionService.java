package com.example.QuestionService.service;

<<<<<<< HEAD

=======
import java.util.List;
>>>>>>> 8109f312478b9a44a342ef4431f4097938d18198
import java.util.Optional;
import java.util.stream.Collectors;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

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
    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);


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
<<<<<<< HEAD


    

=======
>>>>>>> 8109f312478b9a44a342ef4431f4097938d18198
}
