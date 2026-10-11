package com.nexora.backend.controller;

import com.nexora.backend.dto.DashboardResponse;
import com.nexora.backend.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/{userId}")
    public DashboardResponse getDashboard(@PathVariable Long userId) {
        return dashboardService.getDashboard(userId);
    }
}