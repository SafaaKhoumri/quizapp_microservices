package com.example.demo.dto;

import java.util.List;

public class TestDTO {
    private Long id;
    private String name;
    private List<CandidatDTO> candidats;
    private RoleDTO role;
    private LevelDTO level;
    private List<CompetenceDTO> competences;
    private List<QuestionDTO> questions;

    public TestDTO() {
    }
    public TestDTO(Long id, String name, List<CandidatDTO> candidats, RoleDTO role, LevelDTO level,
            List<CompetenceDTO> competences, List<QuestionDTO> questions) {
        this.id = id;
        this.name = name;
        this.candidats = candidats;
        this.role = role;
        this.level = level;
        this.competences = competences;
        this.questions = questions;
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

    public List<CandidatDTO> getCandidats() {
        return candidats;
    }

    public void setCandidats(List<CandidatDTO> candidats) {
        this.candidats = candidats;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public LevelDTO getLevel() {
        return level;
    }

    public void setLevel(LevelDTO level) {
        this.level = level;
    }

    public List<CompetenceDTO> getCompetences() {
        return competences;
    }

    public void setCompetences(List<CompetenceDTO> competences) {
        this.competences = competences;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

}
