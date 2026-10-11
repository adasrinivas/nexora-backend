package com.nexora.backend.service;

import com.nexora.backend.dto.ProgressRequest;
import com.nexora.backend.dto.ProgressResponse;
import com.nexora.backend.model.Progress;
import com.nexora.backend.model.User;
import com.nexora.backend.repository.ProgressRepository;
import com.nexora.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressService {

    private final ProgressRepository progressRepository;
    private final UserRepository userRepository;

    public ProgressService(
            ProgressRepository progressRepository,
            UserRepository userRepository) {

        this.progressRepository = progressRepository;
        this.userRepository = userRepository;
    }

    public ProgressResponse createProgress(ProgressRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        int percentage = Math.max(0, Math.min(100, request.getPercentage()));

        Progress progress = new Progress(
                request.getSkillName(),
                percentage,
                user
        );

        Progress savedProgress = progressRepository.save(progress);

        return convertToResponse(savedProgress);
    }

    public List<ProgressResponse> getProgressByUserId(Long userId) {

        return progressRepository.findByUserId(userId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public ProgressResponse updateProgress(Long id, ProgressRequest request) {

        Progress progress = progressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress entry not found"));

        if (request.getSkillName() != null && !request.getSkillName().trim().isEmpty()) {
            progress.setSkillName(request.getSkillName().trim());
        }
        if (request.getPercentage() >= 0) {
            progress.setPercentage(Math.max(0, Math.min(100, request.getPercentage())));
        }

        Progress updated = progressRepository.save(progress);
        return convertToResponse(updated);
    }

    public void deleteProgress(Long id) {

        Progress progress = progressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress entry not found"));

        progressRepository.delete(progress);
    }

    private ProgressResponse convertToResponse(Progress progress) {
        return new ProgressResponse(
                progress.getId(),
                progress.getSkillName(),
                progress.getPercentage(),
                progress.getUser() != null ? progress.getUser().getId() : null
        );
    }
}