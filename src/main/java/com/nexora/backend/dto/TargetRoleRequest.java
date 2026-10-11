package com.nexora.backend.dto;

public class TargetRoleRequest {

    private Long userId;
    private Long careerRoleId;

    public TargetRoleRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCareerRoleId() {
        return careerRoleId;
    }

    public void setCareerRoleId(Long careerRoleId) {
        this.careerRoleId = careerRoleId;
    }
}