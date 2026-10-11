package com.nexora.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "required_skills")
public class RequiredSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skillName;

    private String requiredLevel;

    @ManyToOne
    @JoinColumn(name = "career_role_id")
    private CareerRole careerRole;

    public RequiredSkill() {
    }

    public RequiredSkill(
            String skillName,
            String requiredLevel,
            CareerRole careerRole) {

        this.skillName = skillName;
        this.requiredLevel = requiredLevel;
        this.careerRole = careerRole;
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

    public CareerRole getCareerRole() {
        return careerRole;
    }

    public void setCareerRole(CareerRole careerRole) {
        this.careerRole = careerRole;
    }
}
