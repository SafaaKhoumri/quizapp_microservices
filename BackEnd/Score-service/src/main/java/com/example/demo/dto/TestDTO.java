package com.example.demo.dto;

import java.util.List;

public class TestDTO {
    private Long id;
    private String name;
    private List<CandidatDTO> candidates;

    // Constructeurs
    public TestDTO() {
    }

    public TestDTO(Long id, String name, List<CandidatDTO> candidates) {
        this.id = id;
        this.name = name;
        this.candidates = candidates;
    }

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

    public List<CandidatDTO> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<CandidatDTO> candidates) {
        this.candidates = candidates;
    }
}

