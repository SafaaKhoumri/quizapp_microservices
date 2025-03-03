package com.example.QuestionService.service;


import java.util.Optional;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import com.example.QuestionService.dto.QuestionAnswerDTO;
import com.example.QuestionService.dto.QuestionDTO;
import com.example.QuestionService.model.Question;
import com.example.QuestionService.repository.QuestionRepository;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository questionRepository;
    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);


    public QuestionDTO entityToDTO(Question question) {
        if (question == null) {
            return null;
        }

        QuestionDTO dto = new QuestionDTO();
        dto.setId(question.getId());
        dto.setQuestionText(question.getQuestionText());

        return dto;
    }


    public QuestionDTO getQuestionById(Long id){
        return entityToDTO(questionRepository.findById(id).get());
    }

    public QuestionAnswerDTO getQuestionWithAnswers(Long id) {
        Optional<Question> questionOptional = questionRepository.findById(id);

        if (questionOptional.isPresent()) {
            Question question = questionOptional.get();
            return new QuestionAnswerDTO(
                question.getId(),
                question.getQuestionText(),
                question.getAnswerChoices()
            );
        } else {
            throw new RuntimeException("Question not found with id: " + id);
        }
    }


    

}
