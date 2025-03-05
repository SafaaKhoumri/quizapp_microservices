package com.example.answer.dto;


public class ScoreDTO {
    private Long candidatId;
    private Long testId;
    private int correctAnswers;
    private int totalQuestions;

    public ScoreDTO() {}

    // Nouveau constructeur prenant en compte le nom du candidat
    public ScoreDTO(Long candidatId, Long testId, int correctAnswers, int totalQuestions) {
        this.candidatId = candidatId;
        this.testId = testId;
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
