package com.example.demo.model;
import java.util.List;


import jakarta.persistence.*;

@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ElementCollection
    private List<Long> candidateIds;

    @ElementCollection
    private List<Long> competenceIds;

    @ElementCollection
    private List<Long> questionIds;

    private Long roleId;
    private Long levelId;
    private Long adminId;
    private Long themeId;

    

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

    public List<Long> getCandidateIds() {
        return candidateIds;
    }

    public void setCandidateIds(List<Long> candidateIds) {
        this.candidateIds = candidateIds;
    }

    public List<Long> getCompetenceIds() {
        return competenceIds;
    }

    public void setCompetenceIds(List<Long> competenceIds) {
        this.competenceIds = competenceIds;
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
    

  

    public Test(Long id, String name, List<Long> candidateIds, List<Long> competenceIds, List<Long> questionIds,
            Long roleId, Long levelId, Long adminId, Long themeId) {
        this.id = id;
        this.name = name;
        this.candidateIds = candidateIds;
        this.competenceIds = competenceIds;
        this.questionIds = questionIds;
        this.roleId = roleId;
        this.levelId = levelId;
        this.adminId = adminId;
        this.themeId = themeId;
    }

    public Test() {
    }

    public List<Long> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Long> questionIds) {
        this.questionIds = questionIds;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getThemeId() {
        return themeId;
    }

    public void setThemeId(Long themeId) {
        this.themeId = themeId;
    }
    
}
