package com.example.demo.model;

public class SendTestRequest {
    private String candidateEmail;
    private String candidateName;
    private Long testId;

    // Getter pour candidateEmail
    public String getCandidateEmail() {
        return candidateEmail;
    }

    // Setter pour candidateEmail
    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    // Setter pour testId
    public void setTestId(Long testId) {
        this.testId = testId;
    }
}
