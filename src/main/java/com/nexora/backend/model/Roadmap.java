package com.nexora.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roadmaps")
public class Roadmap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String skillName;

    private Integer stepNumber;

    @Column(columnDefinition = "boolean default false")
    private Boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Roadmap() {
    }

    public Roadmap(
            String title,
            String description,
            String skillName,
            Integer stepNumber,
            User user) {

        this.title = title;
        this.description = description;
        this.skillName = skillName;
        this.stepNumber = stepNumber;
        this.user = user;
        this.completed = false;
    }

    public Roadmap(
            String title,
            String description,
            String skillName,
            Integer stepNumber,
            Boolean completed,
            User user) {

        this.title = title;
        this.description = description;
        this.skillName = skillName;
        this.stepNumber = stepNumber;
        this.completed = completed != null ? completed : false;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}