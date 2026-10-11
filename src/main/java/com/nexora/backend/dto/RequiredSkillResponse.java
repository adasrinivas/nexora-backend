package com.nexora.backend.dto;

public class RequiredSkillResponse {

    private Long id;
    private String skillName;
    private String requiredLevel;
    private Long careerRoleId;

    public RequiredSkillResponse() {
    }

    public RequiredSkillResponse(
            Long id,
            String skillName,
            String requiredLevel,
            Long careerRoleId) {

        this.id = id;
        this.skillName = skillName;
        this.requiredLevel = requiredLevel;
        this.careerRoleId = careerRoleId;
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