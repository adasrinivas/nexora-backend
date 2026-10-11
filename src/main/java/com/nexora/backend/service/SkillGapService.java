package com.nexora.backend.service;

import com.nexora.backend.dto.SkillGapResponse;
import com.nexora.backend.model.RequiredSkill;
import com.nexora.backend.model.Skill;
import com.nexora.backend.model.User;
import com.nexora.backend.repository.RequiredSkillRepository;
import com.nexora.backend.repository.SkillRepository;
import com.nexora.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SkillGapService {

    private final UserRepository userRepository;
    private final RequiredSkillRepository requiredSkillRepository;
    private final SkillRepository skillRepository;

    public SkillGapService(
            UserRepository userRepository,
            RequiredSkillRepository requiredSkillRepository,
            SkillRepository skillRepository) {

        this.userRepository = userRepository;
        this.requiredSkillRepository = requiredSkillRepository;
        this.skillRepository = skillRepository;
    }

    public List<SkillGapResponse> analyzeSkillGap(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getTargetCareerRole() == null) {
            return new ArrayList<>();
        }

        Long careerRoleId = user.getTargetCareerRole().getId();
        String roleName = user.getTargetCareerRole().getName();

        List<RequiredSkill> requiredSkills =
                requiredSkillRepository.findByCareerRoleId(careerRoleId);

        List<Skill> userSkills =
                skillRepository.findByUserId(userId);

        List<SkillGapResponse> result = new ArrayList<>();

        for (RequiredSkill requiredSkill : requiredSkills) {

            Optional<Skill> matchingSkill = userSkills.stream()
                    .filter(skill -> skill.getName() != null
                            && skill.getName().trim().equalsIgnoreCase(requiredSkill.getSkillName().trim()))
                    .findFirst();

            if (matchingSkill.isEmpty()) {
                // Rule 1: Skill is completely absent
                result.add(new SkillGapResponse(
                        requiredSkill.getSkillName(),
                        requiredSkill.getRequiredLevel(),
                        "None",
                        false,
                        "MISSING",
                        "HIGH",
                        "Critical gap: " + requiredSkill.getSkillName() + " is essential for " + roleName + ". Recommended learning priority: High."
                ));
            } else {
                Skill userSkill = matchingSkill.get();
                String userLevel = userSkill.getLevel() != null ? userSkill.getLevel() : "Beginner";
                String reqLevel = requiredSkill.getRequiredLevel() != null ? requiredSkill.getRequiredLevel() : "Intermediate";

                int userScore = levelToScore(userLevel);
                int reqScore = levelToScore(reqLevel);

                if (userScore >= reqScore) {
                    // Rule 2: Skill meets or exceeds the required level
                    result.add(new SkillGapResponse(
                            requiredSkill.getSkillName(),
                            reqLevel,
                            userLevel,
                            true,
                            "MATCHED",
                            "LOW",
                            "Proficiency satisfied: Your " + userLevel + " level meets the " + reqLevel + " requirement for " + roleName + "."
                    ));
                } else {
                    // Rule 3: Skill is present, but below the required proficiency
                    result.add(new SkillGapResponse(
                            requiredSkill.getSkillName(),
                            reqLevel,
                            userLevel,
                            true,
                            "NEEDS_IMPROVEMENT",
                            "MEDIUM",
                            "Proficiency gap: Currently at " + userLevel + ", but " + reqLevel + " is needed. Build advanced projects to level up."
                    ));
                }
            }
        }

        return result;
    }

    private int levelToScore(String level) {
        if (level == null) return 1;
        return switch (level.trim().toLowerCase()) {
            case "advanced" -> 3;
            case "intermediate" -> 2;
            case "beginner" -> 1;
            default -> 1;
        };
    }
}
