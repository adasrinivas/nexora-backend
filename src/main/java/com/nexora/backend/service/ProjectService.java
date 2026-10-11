
package com.nexora.backend.service;

import com.nexora.backend.dto.ProjectRequest;
import com.nexora.backend.dto.ProjectResponse;
import com.nexora.backend.model.Project;
import com.nexora.backend.model.User;
import com.nexora.backend.repository.ProjectRepository;
import com.nexora.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(
            ProjectRepository projectRepository,
            UserRepository userRepository) {

        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    // Create a new project
    public ProjectResponse createProject(ProjectRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Project project = new Project(
                request.getName(),
                request.getDescription(),
                request.getTechnologies(),
                request.getGithubUrl(),
                request.getLiveUrl(),
                user
        );

        Project savedProject = projectRepository.save(project);

        return convertToResponse(savedProject);
    }

    // Get all projects
    public List<ProjectResponse> getAllProjects() {

        return projectRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // Get projects belonging to a user
    public List<ProjectResponse> getProjectsByUserId(Long userId) {

        return projectRepository.findByUserId(userId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // Update an existing project
    public ProjectResponse updateProject(
            Long projectId,
            ProjectRequest request) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new RuntimeException("Project not found"));

        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setTechnologies(request.getTechnologies());
        project.setGithubUrl(request.getGithubUrl());
        project.setLiveUrl(request.getLiveUrl());

        Project updatedProject = projectRepository.save(project);

        return convertToResponse(updatedProject);
    }

    // Delete a project
    public void deleteProject(Long projectId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() ->
                        new RuntimeException("Project not found"));

        projectRepository.delete(project);
    }

    // Convert Project entity to ProjectResponse
    private ProjectResponse convertToResponse(Project project) {

        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getTechnologies(),
                project.getGithubUrl(),
                project.getLiveUrl(),
                project.getUser() != null
                        ? project.getUser().getId()
                        : null
        );
    }
}
