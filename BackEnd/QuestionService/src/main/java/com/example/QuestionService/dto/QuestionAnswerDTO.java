package com.example.QuestionService.dto;

import java.util.List;

import com.example.QuestionService.model.AnswerChoice;

public class QuestionAnswerDTO {
    private Long id;

    private String questionText;
    private List<AnswerChoice> answerChoices;

    public QuestionAnswerDTO() {
    }

    public QuestionAnswerDTO(Long id, String questionText, List<AnswerChoice> answerChoices) {
        this.id = id;
        this.questionText = questionText;
        this.answerChoices = answerChoices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<AnswerChoice> getAnswerChoices() {
        return answerChoices;
    }

    public void setAnswerChoices(List<AnswerChoice> answerChoices) {
        this.answerChoices = answerChoices;
    }

}
