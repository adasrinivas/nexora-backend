package com.nexora.backend.controller;

import com.nexora.backend.dto.CareerRoleRequest;
import com.nexora.backend.dto.CareerRoleResponse;
import com.nexora.backend.service.CareerRoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/career-roles")
public class CareerRoleController {

    private final CareerRoleService careerRoleService;

    public CareerRoleController(CareerRoleService careerRoleService) {
        this.careerRoleService = careerRoleService;
    }

    @PostMapping
    public CareerRoleResponse createCareerRole(
            @RequestBody CareerRoleRequest request) {

        return careerRoleService.createCareerRole(request);
    }

    @GetMapping
    public List<CareerRoleResponse> getAllCareerRoles() {

        return careerRoleService.getAllCareerRoles();
    }
}
