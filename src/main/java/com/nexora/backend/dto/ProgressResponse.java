package com.nexora.backend.dto;

public class ProgressResponse {

    private Long id;
    private String skillName;
    private Integer percentage;
    private Long userId;

    public ProgressResponse() {
    }

    public ProgressResponse(
            Long id,
            String skillName,
            Integer percentage,
            Long userId) {

        this.id = id;
        this.skillName = skillName;
        this.percentage = percentage;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
