package com.nexora.backend.controller;

import com.nexora.backend.dto.SkillRequest;
import com.nexora.backend.dto.SkillResponse;
import com.nexora.backend.service.SkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public SkillResponse createSkill(@RequestBody SkillRequest request) {
        return skillService.createSkill(request);
    }

    @GetMapping
    public List<SkillResponse> getAllSkills() {
        return skillService.getAllSkills();
    }

    @GetMapping("/user/{userId}")
    public List<SkillResponse> getSkillsByUserId(
            @PathVariable Long userId) {

        return skillService.getSkillsByUserId(userId);
    }

    @PutMapping("/{id}")
    public SkillResponse updateSkill(
            @PathVariable Long id,
            @RequestBody SkillRequest request) {

        return skillService.updateSkill(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
    }
}