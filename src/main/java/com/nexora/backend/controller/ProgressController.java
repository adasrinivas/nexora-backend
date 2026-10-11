package com.nexora.backend.controller;

import com.nexora.backend.dto.ProgressRequest;
import com.nexora.backend.dto.ProgressResponse;
import com.nexora.backend.service.ProgressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping
    public ProgressResponse createProgress(
            @RequestBody ProgressRequest request) {

        return progressService.createProgress(request);
    }

    @GetMapping("/user/{userId}")
    public List<ProgressResponse> getProgressByUserId(
            @PathVariable Long userId) {

        return progressService.getProgressByUserId(userId);
    }

    @PutMapping("/{id}")
    public ProgressResponse updateProgress(
            @PathVariable Long id,
            @RequestBody ProgressRequest request) {

        return progressService.updateProgress(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteProgress(@PathVariable Long id) {
        progressService.deleteProgress(id);
    }
}