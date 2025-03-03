package com.example.demo.model;

import java.util.List;

import com.example.demo.dto.CandidatDTO;

public  class TestRequest {
    private String testName;
    private String adminEmail;
    private Long domaineId;
    private Long roleId;
    private Long levelId;
    private List<Long> competencyIds;
    private List<CandidatDTO> candidates;

    // Getters and setters
    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public Long getDomaineId() {
        return domaineId;
    }

    public void setDomaineId(Long domaineId) {
        this.domaineId = domaineId;
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

    public List<Long> getCompetencyIds() {
        return competencyIds;
    }

    public void setCompetencyIds(List<Long> competencyIds) {
        this.competencyIds = competencyIds;
    }

    public List<CandidatDTO> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<CandidatDTO> candidates) {
        this.candidates = candidates;
    }
}