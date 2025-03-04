package com.example.demo.dto;


public class ScoreDTO {
    private Long candidatId;
    private Long testId;
    private String candidatName; // Ajout du champ pour stocker le nom du candidat
    private int correctAnswers;
    private int totalQuestions;

    public ScoreDTO() {}

    // Nouveau constructeur prenant en compte le nom du candidat
    public ScoreDTO(Long candidatId, Long testId, String candidatName, int correctAnswers, int totalQuestions) {
        this.candidatId = candidatId;
        this.testId = testId;
        this.candidatName = candidatName;
        this.correctAnswers = correctAnswers;
        this.totalQuestions = totalQuestions;
    }

    public Long getCandidatId() {
        return candidatId;
    }

    public void setCandidatId(Long candidatId) {
        this.candidatId = candidatId;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getCandidatName() {
        return candidatName;
    }

    public void setCandidatName(String candidatName) {
        this.candidatName = candidatName;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getScorePercentage() {
        if (totalQuestions == 0) return 0;
        return (int) ((correctAnswers / (double) totalQuestions) * 100);
    }
}
