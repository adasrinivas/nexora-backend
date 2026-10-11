package com.nexora.backend.controller;

import com.nexora.backend.dto.RequiredSkillRequest;
import com.nexora.backend.dto.RequiredSkillResponse;
import com.nexora.backend.service.RequiredSkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/required-skills")
public class RequiredSkillController {

    private final RequiredSkillService requiredSkillService;

    public RequiredSkillController(
            RequiredSkillService requiredSkillService) {

        this.requiredSkillService = requiredSkillService;
    }

    @PostMapping
    public RequiredSkillResponse createRequiredSkill(
            @RequestBody RequiredSkillRequest request) {

        return requiredSkillService.createRequiredSkill(request);
    }

    @GetMapping("/career-role/{careerRoleId}")
    public List<RequiredSkillResponse> getSkillsByCareerRole(
            @PathVariable Long careerRoleId) {

        return requiredSkillService
                .getSkillsByCareerRole(careerRoleId);
    }
}