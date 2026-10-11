package com.nexora.backend.dto;

public class RoadmapResponse {

    private Long id;
    private String title;
    private String description;
    private String skillName;
    private Integer stepNumber;
    private Boolean completed;
    private Long userId;

    public RoadmapResponse() {
    }

    public RoadmapResponse(
            Long id,
            String title,
            String description,
            String skillName,
            Integer stepNumber,
            Long userId) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.skillName = skillName;
        this.stepNumber = stepNumber;
        this.completed = false;
        this.userId = userId;
    }

    public RoadmapResponse(
            Long id,
            String title,
            String description,
            String skillName,
            Integer stepNumber,
            Boolean completed,
            Long userId) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.skillName = skillName;
        this.stepNumber = stepNumber;
        this.completed = completed != null ? completed : false;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Integer getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }

    public Boolean getCompleted() {
        return completed != null && completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed != null && completed;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
