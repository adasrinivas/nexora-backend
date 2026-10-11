package com.nexora.backend.dto;

public class SkillResponse {

    private Long id;
    private String name;
    private String level;
    private Long userId;

    public SkillResponse() {
    }

    public SkillResponse(Long id, String name, String level, Long userId) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.userId = userId;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}