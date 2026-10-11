package com.nexora.backend.controller;

import com.nexora.backend.dto.TargetRoleRequest;
import com.nexora.backend.service.TargetRoleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/target-role")
public class TargetRoleController {

    private final TargetRoleService targetRoleService;

    public TargetRoleController(TargetRoleService targetRoleService) {
        this.targetRoleService = targetRoleService;
    }

    @PostMapping
    public String setTargetRole(
            @RequestBody TargetRoleRequest request) {

        return targetRoleService.setTargetRole(request);
    }
}
