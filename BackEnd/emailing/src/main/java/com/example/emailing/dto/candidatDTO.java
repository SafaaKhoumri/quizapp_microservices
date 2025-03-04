package com.example.emailing.dto;

public class candidatDTO {
    private Long id;
    private String name;
    private String email;
    private Long testId; // ✅ Add this field
    private String testName; // ✅ Add this field

    // Getters et Setters
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

    public Long getTestId() { // ✅ New Getter
        return testId;
    }

    public void setTestId(Long testId) { // ✅ New Setter
        this.testId = testId;
    }

    public String getTestName() { // ✅ New Getter
        return testName;
    }

    public void setTestName(String testName) { // ✅ New Setter
        this.testName = testName;
    }
}
