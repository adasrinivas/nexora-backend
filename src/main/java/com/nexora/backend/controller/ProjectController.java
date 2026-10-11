
package com.nexora.backend.controller;

import com.nexora.backend.dto.ProjectRequest;
import com.nexora.backend.dto.ProjectResponse;
import com.nexora.backend.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ProjectResponse createProject(
            @RequestBody ProjectRequest request) {
        return projectService.createProject(request);
    }

    @GetMapping
    public List<ProjectResponse> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/user/{userId}")
    public List<ProjectResponse> getProjectsByUserId(
            @PathVariable Long userId) {
        return projectService.getProjectsByUserId(userId);
    }

    @PutMapping("/{projectId}")
    public ProjectResponse updateProject(
            @PathVariable Long projectId,
            @RequestBody ProjectRequest request) {
        return projectService.updateProject(projectId, request);
    }

    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
    }
}
