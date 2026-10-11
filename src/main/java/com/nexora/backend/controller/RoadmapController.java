package com.nexora.backend.controller;

import com.nexora.backend.dto.RoadmapRequest;
import com.nexora.backend.dto.RoadmapResponse;
import com.nexora.backend.service.RoadmapService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roadmaps")
public class RoadmapController {

    private final RoadmapService roadmapService;

    public RoadmapController(RoadmapService roadmapService) {
        this.roadmapService = roadmapService;
    }

    @PostMapping
    public RoadmapResponse createRoadmapStep(
            @RequestBody RoadmapRequest request) {

        return roadmapService.createRoadmapStep(request);
    }

    @GetMapping("/user/{userId}")
    public List<RoadmapResponse> getRoadmapByUserId(
            @PathVariable Long userId) {

        return roadmapService.getRoadmapByUserId(userId);
    }

    @PutMapping("/{id}")
    public RoadmapResponse updateRoadmapStep(
            @PathVariable Long id,
            @RequestBody RoadmapRequest request) {

        return roadmapService.updateRoadmapStep(id, request);
    }

    @PatchMapping("/{id}/toggle")
    public RoadmapResponse toggleStepCompletion(
            @PathVariable Long id) {

        return roadmapService.toggleStepCompletion(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRoadmapStep(
            @PathVariable Long id) {

        roadmapService.deleteRoadmapStep(id);
    }

    @PostMapping("/generate/{userId}")
    public List<RoadmapResponse> generateRoadmapForUser(
            @PathVariable Long userId) {

        return roadmapService.generateRoadmapForUser(userId);
    }
}