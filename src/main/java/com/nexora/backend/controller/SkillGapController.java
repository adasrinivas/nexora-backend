package com.nexora.backend.controller;

import com.nexora.backend.dto.SkillGapResponse;
import com.nexora.backend.service.SkillGapService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skill-gap")
public class SkillGapController {

    private final SkillGapService skillGapService;

    public SkillGapController(SkillGapService skillGapService) {
        this.skillGapService = skillGapService;
    }

    @GetMapping("/{userId}")
    public List<SkillGapResponse> analyzeSkillGap(
            @PathVariable Long userId) {

        return skillGapService.analyzeSkillGap(userId);
    }
}
