package com.nexora.backend.dto;

public class SkillGapResponse {

    private String skillName;
    private String requiredLevel;
    private String currentLevel;
    private boolean available;
    private String status;        // MATCHED, NEEDS_IMPROVEMENT, MISSING
    private String priority;      // HIGH, MEDIUM, LOW
    private String recommendation;

    public SkillGapResponse() {
    }

    public SkillGapResponse(
            String skillName,
            String requiredLevel,
            boolean available) {

        this.skillName = skillName;
        this.requiredLevel = requiredLevel;
        this.available = available;
        this.currentLevel = available ? requiredLevel : "None";
        this.status = available ? "MATCHED" : "MISSING";
        this.priority = available ? "LOW" : "HIGH";
        this.recommendation = available
                ? "Skill matches role expectation."
                : "Missing skill. Recommended learning priority: High.";
    }

    public SkillGapResponse(
            String skillName,
            String requiredLevel,
            String currentLevel,
            boolean available,
            String status,
            String priority,
            String recommendation) {

        this.skillName = skillName;
        this.requiredLevel = requiredLevel;
        this.currentLevel = currentLevel;
        this.available = available;
        this.status = status;
        this.priority = priority;
        this.recommendation = recommendation;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(String requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public String getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(String currentLevel) {
        this.currentLevel = currentLevel;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}