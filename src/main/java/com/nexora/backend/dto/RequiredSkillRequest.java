package com.nexora.backend.dto;

public class RequiredSkillRequest {

    private String skillName;
    private String requiredLevel;
    private Long careerRoleId;

    public RequiredSkillRequest() {
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

    public Long getCareerRoleId() {
        return careerRoleId;
    }

    public void setCareerRoleId(Long careerRoleId) {
        this.careerRoleId = careerRoleId;
    }
}
