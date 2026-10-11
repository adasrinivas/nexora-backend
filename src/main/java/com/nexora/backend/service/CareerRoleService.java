package com.nexora.backend.service;

import com.nexora.backend.dto.CareerRoleRequest;
import com.nexora.backend.dto.CareerRoleResponse;
import com.nexora.backend.model.CareerRole;
import com.nexora.backend.repository.CareerRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareerRoleService {

    private final CareerRoleRepository careerRoleRepository;

    public CareerRoleService(CareerRoleRepository careerRoleRepository) {
        this.careerRoleRepository = careerRoleRepository;
    }

    public CareerRoleResponse createCareerRole(CareerRoleRequest request) {

        CareerRole careerRole = new CareerRole(
                request.getName(),
                request.getDescription()
        );

        CareerRole savedRole = careerRoleRepository.save(careerRole);

        return new CareerRoleResponse(
                savedRole.getId(),
                savedRole.getName(),
                savedRole.getDescription()
        );
    }

    public List<CareerRoleResponse> getAllCareerRoles() {

        return careerRoleRepository.findAll()
                .stream()
                .map(role -> new CareerRoleResponse(
                        role.getId(),
                        role.getName(),
                        role.getDescription()
                ))
                .toList();
    }
}
