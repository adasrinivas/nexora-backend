package com.nexora.backend.service;

import com.nexora.backend.dto.SkillRequest;
import com.nexora.backend.dto.SkillResponse;
import com.nexora.backend.model.Skill;
import com.nexora.backend.model.User;
import com.nexora.backend.repository.SkillRepository;
import com.nexora.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    public SkillService(
            SkillRepository skillRepository,
            UserRepository userRepository) {

        this.skillRepository = skillRepository;
        this.userRepository = userRepository;
    }

    public SkillResponse createSkill(SkillRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Skill skill = new Skill(
                request.getName(),
                request.getLevel(),
                user
        );

        Skill savedSkill = skillRepository.save(skill);

        return new SkillResponse(
                savedSkill.getId(),
                savedSkill.getName(),
                savedSkill.getLevel(),
                savedSkill.getUser().getId()
        );
    }

    public List<SkillResponse> getAllSkills() {

        return skillRepository.findAll()
                .stream()
                .map(skill -> new SkillResponse(
                        skill.getId(),
                        skill.getName(),
                        skill.getLevel(),
                        skill.getUser() != null
                                ? skill.getUser().getId()
                                : null
                ))
                .toList();
    }

    public List<SkillResponse> getSkillsByUserId(Long userId) {

        return skillRepository.findByUserId(userId)
                .stream()
                .map(skill -> new SkillResponse(
                        skill.getId(),
                        skill.getName(),
                        skill.getLevel(),
                        skill.getUser() != null
                                ? skill.getUser().getId()
                                : null
                ))
                .toList();
    }

    public SkillResponse updateSkill(Long skillId, SkillRequest request) {
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        if (request.getName() != null && !request.getName().trim().isEmpty()) {
            skill.setName(request.getName().trim());
        }
        if (request.getLevel() != null && !request.getLevel().trim().isEmpty()) {
            skill.setLevel(request.getLevel().trim());
        }

        Skill updatedSkill = skillRepository.save(skill);

        return new SkillResponse(
                updatedSkill.getId(),
                updatedSkill.getName(),
                updatedSkill.getLevel(),
                updatedSkill.getUser() != null ? updatedSkill.getUser().getId() : null
        );
    }

    public void deleteSkill(Long skillId) {
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
        skillRepository.delete(skill);
    }
}