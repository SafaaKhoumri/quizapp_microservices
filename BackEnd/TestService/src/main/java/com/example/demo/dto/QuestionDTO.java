package com.example.demo.dto;

import java.util.List;

public class QuestionDTO {
    private Long id;

    private String questionText;
    private List<AnswerChoiceDTO> answerChoices;

    public QuestionDTO() {
    }

    public QuestionDTO(Long id, String questionText, List<AnswerChoiceDTO> answerChoices) {
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

    public List<AnswerChoiceDTO> getAnswerChoices() {
        return answerChoices;
    }

    public void setAnswerChoices(List<AnswerChoiceDTO> answerChoices) {
        this.answerChoices = answerChoices;
    }

}
