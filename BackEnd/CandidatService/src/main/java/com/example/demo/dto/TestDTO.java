package com.example.demo.dto;

import java.util.List;

public class TestDTO {
    private Long id;
    private String name;
    private List<CandidatDTO> candidats;
    private Long roleId;
    private Long levelId;

    public TestDTO() {
    }

    public TestDTO(Long id, String name, List<CandidatDTO> candidats, Long roleId, Long levelId) {
        this.id = id;
        this.name = name;
        this.candidats = candidats;
        this.roleId = roleId;
        this.levelId = levelId;
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public List<CandidatDTO> getCandidats() {
        return candidats;
    }

    public void setCandidats(List<CandidatDTO> candidats) {
        this.candidats = candidats;
    }
}