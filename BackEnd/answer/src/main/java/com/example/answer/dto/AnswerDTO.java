package com.example.answer.dto;

public class AnswerDTO {
    private Long id;
    private String texteReponse;
    private boolean estCorrecte;
    private Long candidatId;  
    private Long questionId;  

    // Default constructor
    public AnswerDTO() {}

    // Constructor to initialize fields
    public AnswerDTO(Long id, String texteReponse, boolean estCorrecte, Long candidatId, Long questionId) {
        this.id = id;
        this.texteReponse = texteReponse;
        this.estCorrecte = estCorrecte;
        this.candidatId = candidatId;
        this.questionId = questionId;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexteReponse() {
        return texteReponse;
    }

    public void setTexteReponse(String texteReponse) {
        this.texteReponse = texteReponse;
    }

    public boolean isEstCorrecte() {
        return estCorrecte;
    }

    public void setEstCorrecte(boolean estCorrecte) {
        this.estCorrecte = estCorrecte;
    }

    public Long getCandidatId() {
        return candidatId;
    }

    public void setCandidatId(Long candidatId) {
        this.candidatId = candidatId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Override
    public String toString() {
        return "AnswerDTO{" +
                "id=" + id +
                ", texteReponse='" + texteReponse + '\'' +
                ", estCorrecte=" + estCorrecte +
                ", candidatId=" + candidatId +
                ", questionId=" + questionId +
                '}';
    }
}
