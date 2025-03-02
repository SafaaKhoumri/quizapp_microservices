package com.example.CompetenceService.dto;

public class CompetenceDTO {
    private Long id;
    private String name;
    private Long roleId;
    private Long levelId;

    public CompetenceDTO() {
    }

  

    public CompetenceDTO(Long id, String name, Long roleId, Long levelId) {
        this.id = id;
        this.name = name;
        this.roleId = roleId;
        this.levelId = levelId;
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

}