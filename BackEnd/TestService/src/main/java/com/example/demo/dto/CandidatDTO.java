package com.example.demo.dto;

public class CandidatDTO {

    private Long id;
    private String name;
    private String email;
    private Long testId;

    public CandidatDTO() {
    }
        public CandidatDTO(Long id, String name, String email, Long testId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.testId = testId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTestId() {
        return testId;
    }
    
    public void setTestId(Long testId) {
        this.testId = testId;
    }
}
