package com.nexora.backend.service;

import com.nexora.backend.dto.RoadmapRequest;
import com.nexora.backend.dto.RoadmapResponse;
import com.nexora.backend.model.CareerRole;
import com.nexora.backend.model.RequiredSkill;
import com.nexora.backend.model.Roadmap;
import com.nexora.backend.model.User;
import com.nexora.backend.repository.RequiredSkillRepository;
import com.nexora.backend.repository.RoadmapRepository;
import com.nexora.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoadmapService {

    private final RoadmapRepository roadmapRepository;
    private final UserRepository userRepository;
    private final RequiredSkillRepository requiredSkillRepository;

    public RoadmapService(
            RoadmapRepository roadmapRepository,
            UserRepository userRepository,
            RequiredSkillRepository requiredSkillRepository) {

        this.roadmapRepository = roadmapRepository;
        this.userRepository = userRepository;
        this.requiredSkillRepository = requiredSkillRepository;
    }

    public RoadmapResponse createRoadmapStep(RoadmapRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Roadmap roadmap = new Roadmap(
                request.getTitle(),
                request.getDescription(),
                request.getSkillName(),
                request.getStepNumber(),
                request.getCompleted() != null ? request.getCompleted() : false,
                user
        );

        Roadmap savedRoadmap = roadmapRepository.save(roadmap);

        return convertToResponse(savedRoadmap);
    }

    public List<RoadmapResponse> getRoadmapByUserId(Long userId) {

        return roadmapRepository.findByUserIdOrderByStepNumberAsc(userId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public RoadmapResponse toggleStepCompletion(Long stepId) {

        Roadmap step = roadmapRepository.findById(stepId)
                .orElseThrow(() -> new RuntimeException("Roadmap step not found"));

        step.setCompleted(!step.getCompleted());

        Roadmap saved = roadmapRepository.save(step);
        return convertToResponse(saved);
    }

    public RoadmapResponse updateRoadmapStep(Long stepId, RoadmapRequest request) {

        Roadmap step = roadmapRepository.findById(stepId)
                .orElseThrow(() -> new RuntimeException("Roadmap step not found"));

        if (request.getTitle() != null) step.setTitle(request.getTitle());
        if (request.getDescription() != null) step.setDescription(request.getDescription());
        if (request.getSkillName() != null) step.setSkillName(request.getSkillName());
        if (request.getStepNumber() != null) step.setStepNumber(request.getStepNumber());
        if (request.getCompleted() != null) step.setCompleted(request.getCompleted());

        Roadmap saved = roadmapRepository.save(step);
        return convertToResponse(saved);
    }

    public void deleteRoadmapStep(Long stepId) {

        Roadmap step = roadmapRepository.findById(stepId)
                .orElseThrow(() -> new RuntimeException("Roadmap step not found"));

        roadmapRepository.delete(step);
    }

    @Transactional
    public List<RoadmapResponse> generateRoadmapForUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CareerRole targetRole = user.getTargetCareerRole();
        if (targetRole == null) {
            throw new IllegalArgumentException("Please select a target career role first.");
        }

        List<RequiredSkill> requiredSkills =
                requiredSkillRepository.findByCareerRoleId(targetRole.getId());

        if (requiredSkills.isEmpty()) {
            throw new IllegalArgumentException("No required skills configured for target role: " + targetRole.getName());
        }

        // Remove previous steps for a clean, fresh roadmap for the newly chosen role
        List<Roadmap> existingSteps = roadmapRepository.findByUserId(userId);
        roadmapRepository.deleteAll(existingSteps);

        List<Roadmap> generated = new ArrayList<>();
        int stepNumber = 1;

        for (RequiredSkill req : requiredSkills) {
            String skill = req.getSkillName();
            String level = req.getRequiredLevel();

            String title = "Master " + skill + " (" + level + " Level)";
            String description = "Learn core concepts, best practices, and hands-on implementation of " +
                    skill + " to reach " + level + " proficiency for " + targetRole.getName() + ".";

            Roadmap step = new Roadmap(
                    title,
                    description,
                    skill,
                    stepNumber++,
                    false,
                    user
            );
            generated.add(step);
        }

        // Add a final capstone step
        Roadmap capstone = new Roadmap(
                "Build Full-Stack Portfolio Project",
                "Integrate " + targetRole.getName() + " technologies into an end-to-end production-ready project with documentation.",
                "Full Stack Integration",
                stepNumber,
                false,
                user
        );
        generated.add(capstone);

        roadmapRepository.saveAll(generated);

        return getRoadmapByUserId(userId);
    }

    private RoadmapResponse convertToResponse(Roadmap step) {
        return new RoadmapResponse(
                step.getId(),
                step.getTitle(),
                step.getDescription(),
                step.getSkillName(),
                step.getStepNumber(),
                step.getCompleted(),
                step.getUser() != null ? step.getUser().getId() : null
        );
    }
}