package com.example.demo.model;
import jakarta.persistence.*;

@Entity
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long candidatId;
    private Long testId;
    private int correctAnswers;
    private int totalQuestions;

    public Score() {}

    public Score(Long candidatId, Long testId, int correctAnswers, int totalQuestions) {
        this.candidatId = candidatId;
        this.testId = testId;
        this.correctAnswers = correctAnswers;
        this.totalQuestions = totalQuestions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
