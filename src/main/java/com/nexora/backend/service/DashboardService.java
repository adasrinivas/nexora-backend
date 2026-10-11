package com.nexora.backend.service;

import com.nexora.backend.dto.*;
import com.nexora.backend.model.User;
import com.nexora.backend.repository.*;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final UserRepository userRepository;
    private final SkillService skillService;
    private final ProjectService projectService;
    private final CertificationService certificationService;
    private final RoadmapService roadmapService;
    private final ProgressService progressService;
    private final SkillGapService skillGapService;

    public DashboardService(
            UserRepository userRepository,
            SkillService skillService,
            ProjectService projectService,
            CertificationService certificationService,
            RoadmapService roadmapService,
            ProgressService progressService,
            SkillGapService skillGapService) {

        this.userRepository = userRepository;
        this.skillService = skillService;
        this.projectService = projectService;
        this.certificationService = certificationService;
        this.roadmapService = roadmapService;
        this.progressService = progressService;
        this.skillGapService = skillGapService;
    }

    public DashboardResponse getDashboard(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CareerRoleResponse targetRole = null;
        if (user.getTargetCareerRole() != null) {
            targetRole = new CareerRoleResponse(
                    user.getTargetCareerRole().getId(),
                    user.getTargetCareerRole().getName(),
                    user.getTargetCareerRole().getDescription()
            );
        }

        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                targetRole
        );

        return new DashboardResponse(
                userResponse,
                skillService.getSkillsByUserId(userId),
                projectService.getProjectsByUserId(userId),
                certificationService.getCertificationsByUserId(userId),
                roadmapService.getRoadmapByUserId(userId),
                progressService.getProgressByUserId(userId),
                skillGapService.analyzeSkillGap(userId)
        );
    }
}