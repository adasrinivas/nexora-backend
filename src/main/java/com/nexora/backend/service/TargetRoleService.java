package com.nexora.backend.service;

import com.nexora.backend.dto.TargetRoleRequest;
import com.nexora.backend.model.CareerRole;
import com.nexora.backend.model.User;
import com.nexora.backend.repository.CareerRoleRepository;
import com.nexora.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TargetRoleService {

    private final UserRepository userRepository;
    private final CareerRoleRepository careerRoleRepository;

    public TargetRoleService(
            UserRepository userRepository,
            CareerRoleRepository careerRoleRepository) {

        this.userRepository = userRepository;
        this.careerRoleRepository = careerRoleRepository;
    }

    public String setTargetRole(TargetRoleRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        CareerRole careerRole = careerRoleRepository
                .findById(request.getCareerRoleId())
                .orElseThrow(() -> new RuntimeException("Career role not found"));

        user.setTargetCareerRole(careerRole);

        userRepository.save(user);

        return "Target career role assigned successfully";
    }
}
