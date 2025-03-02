package com.example.LevelService.dto;

public class LevelDTO {
    private Long id;
    private String name;

    private ThemeDTO theme;

    public LevelDTO() {
    }

    public LevelDTO(Long id, String name, ThemeDTO theme) {
        this.id = id;
        this.name = name;
        this.theme = theme;
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

    public ThemeDTO getTheme() {
        return theme;
    }

    public void setTheme(ThemeDTO theme) {
        this.theme = theme;
    }

}
