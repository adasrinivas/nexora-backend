package com.nexora.backend.dto;

import java.util.List;

public class DashboardResponse {

    private UserResponse user;
    private List<SkillResponse> skills;
    private List<ProjectResponse> projects;
    private List<CertificationResponse> certifications;
    private List<RoadmapResponse> roadmap;
    private List<ProgressResponse> progress;
    private List<SkillGapResponse> skillGaps;

    public DashboardResponse() {
    }

    public DashboardResponse(
            UserResponse user,
            List<SkillResponse> skills,
            List<ProjectResponse> projects,
            List<CertificationResponse> certifications,
            List<RoadmapResponse> roadmap,
            List<ProgressResponse> progress,
            List<SkillGapResponse> skillGaps) {

        this.user = user;
        this.skills = skills;
        this.projects = projects;
        this.certifications = certifications;
        this.roadmap = roadmap;
        this.progress = progress;
        this.skillGaps = skillGaps;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public List<SkillResponse> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillResponse> skills) {
        this.skills = skills;
    }

    public List<ProjectResponse> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectResponse> projects) {
        this.projects = projects;
    }

    public List<CertificationResponse> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<CertificationResponse> certifications) {
        this.certifications = certifications;
    }

    public List<RoadmapResponse> getRoadmap() {
        return roadmap;
    }

    public void setRoadmap(List<RoadmapResponse> roadmap) {
        this.roadmap = roadmap;
    }

    public List<ProgressResponse> getProgress() {
        return progress;
    }

    public void setProgress(List<ProgressResponse> progress) {
        this.progress = progress;
    }

    public List<SkillGapResponse> getSkillGaps() {
        return skillGaps;
    }

    public void setSkillGaps(List<SkillGapResponse> skillGaps) {
        this.skillGaps = skillGaps;
    }
}