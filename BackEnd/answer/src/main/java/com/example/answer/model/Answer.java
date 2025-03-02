package com.example.answer.model;
import jakarta.persistence.*;
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texteReponse;
    private boolean estCorrecte;
    private Long candidatId;
    private Long questionId;
    
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
    public Answer(Long id, String texteReponse, boolean estCorrecte, Long candidatId, Long questionId) {
        this.id = id;
        this.texteReponse = texteReponse;
        this.estCorrecte = estCorrecte;
        this.candidatId = candidatId;
        this.questionId = questionId;
    }
    public Answer() {
    }

    
}
