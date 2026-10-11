package com.nexora.backend.service;

import com.nexora.backend.dto.RequiredSkillRequest;
import com.nexora.backend.dto.RequiredSkillResponse;
import com.nexora.backend.model.CareerRole;
import com.nexora.backend.model.RequiredSkill;
import com.nexora.backend.repository.CareerRoleRepository;
import com.nexora.backend.repository.RequiredSkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequiredSkillService {

    private final RequiredSkillRepository requiredSkillRepository;
    private final CareerRoleRepository careerRoleRepository;

    public RequiredSkillService(
            RequiredSkillRepository requiredSkillRepository,
            CareerRoleRepository careerRoleRepository) {

        this.requiredSkillRepository = requiredSkillRepository;
        this.careerRoleRepository = careerRoleRepository;
    }

    public RequiredSkillResponse createRequiredSkill(
            RequiredSkillRequest request) {

        CareerRole careerRole = careerRoleRepository
                .findById(request.getCareerRoleId())
                .orElseThrow(() -> new RuntimeException("Career role not found"));

        RequiredSkill requiredSkill = new RequiredSkill(
                request.getSkillName(),
                request.getRequiredLevel(),
                careerRole
        );

        RequiredSkill savedSkill =
                requiredSkillRepository.save(requiredSkill);

        return new RequiredSkillResponse(
                savedSkill.getId(),
                savedSkill.getSkillName(),
                savedSkill.getRequiredLevel(),
                savedSkill.getCareerRole().getId()
        );
    }

    public List<RequiredSkillResponse> getSkillsByCareerRole(
            Long careerRoleId) {

        return requiredSkillRepository
                .findByCareerRoleId(careerRoleId)
                .stream()
                .map(skill -> new RequiredSkillResponse(
                        skill.getId(),
                        skill.getSkillName(),
                        skill.getRequiredLevel(),
                        skill.getCareerRole().getId()
                ))
                .toList();
    }
}
