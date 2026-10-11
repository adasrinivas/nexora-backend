package com.nexora.backend.config;

import com.nexora.backend.model.CareerRole;
import com.nexora.backend.model.RequiredSkill;
import com.nexora.backend.repository.CareerRoleRepository;
import com.nexora.backend.repository.RequiredSkillRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer implements CommandLineRunner {

    private final CareerRoleRepository careerRoleRepository;
    private final RequiredSkillRepository requiredSkillRepository;

    public DataInitializer(
            CareerRoleRepository careerRoleRepository,
            RequiredSkillRepository requiredSkillRepository) {

        this.careerRoleRepository = careerRoleRepository;
        this.requiredSkillRepository = requiredSkillRepository;
    }

    @Override
    public void run(String... args) {
        initRolesAndSkills();
    }

    private void initRolesAndSkills() {
        // 1. Java Full Stack Developer
        CareerRole fullStackRole = getOrCreateRole(
                "Java Full Stack Developer",
                "Develops full-stack applications using Java, Spring Boot, React, and MySQL databases."
        );
        ensureSkill(fullStackRole, "Java", "Advanced");
        ensureSkill(fullStackRole, "Spring Boot", "Advanced");
        ensureSkill(fullStackRole, "React", "Intermediate");
        ensureSkill(fullStackRole, "MySQL", "Intermediate");
        ensureSkill(fullStackRole, "REST APIs", "Advanced");
        ensureSkill(fullStackRole, "Git", "Intermediate");

        // 2. Frontend Developer
        CareerRole frontendRole = getOrCreateRole(
                "Frontend Developer",
                "Specializes in building modern, interactive, responsive web user interfaces."
        );
        ensureSkill(frontendRole, "React", "Advanced");
        ensureSkill(frontendRole, "JavaScript", "Advanced");
        ensureSkill(frontendRole, "HTML & CSS", "Advanced");
        ensureSkill(frontendRole, "TypeScript", "Intermediate");
        ensureSkill(frontendRole, "Git", "Intermediate");

        // 3. Backend Developer
        CareerRole backendRole = getOrCreateRole(
                "Backend Developer",
                "Architects secure server-side systems, RESTful microservices, and high-performance databases."
        );
        ensureSkill(backendRole, "Java", "Advanced");
        ensureSkill(backendRole, "Spring Boot", "Advanced");
        ensureSkill(backendRole, "MySQL", "Advanced");
        ensureSkill(backendRole, "Docker", "Intermediate");
        ensureSkill(backendRole, "REST APIs", "Advanced");

        // 4. Data Analyst / AI Specialist
        CareerRole dataRole = getOrCreateRole(
                "Data Analyst & AI Specialist",
                "Analyzes structured data, builds machine learning models, and creates business dashboards."
        );
        ensureSkill(dataRole, "Python", "Advanced");
        ensureSkill(dataRole, "SQL", "Advanced");
        ensureSkill(dataRole, "Data Visualization", "Intermediate");
        ensureSkill(dataRole, "Machine Learning", "Intermediate");
    }

    private CareerRole getOrCreateRole(String name, String description) {
        return careerRoleRepository.findAll().stream()
                .filter(role -> role.getName() != null && role.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseGet(() -> careerRoleRepository.save(new CareerRole(name, description)));
    }

    private void ensureSkill(CareerRole role, String skillName, String level) {
        List<RequiredSkill> existing = requiredSkillRepository.findByCareerRoleId(role.getId());
        boolean exists = existing.stream()
                .anyMatch(s -> s.getSkillName() != null && s.getSkillName().equalsIgnoreCase(skillName));

        if (!exists) {
            requiredSkillRepository.save(new RequiredSkill(skillName, level, role));
        }
    }
}
