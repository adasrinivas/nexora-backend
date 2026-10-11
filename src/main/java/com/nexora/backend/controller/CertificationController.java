package com.nexora.backend.controller;

import com.nexora.backend.dto.CertificationRequest;
import com.nexora.backend.dto.CertificationResponse;
import com.nexora.backend.service.CertificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certifications")
public class CertificationController {

    private final CertificationService certificationService;

    public CertificationController(
            CertificationService certificationService) {

        this.certificationService = certificationService;
    }

    @PostMapping
    public CertificationResponse createCertification(
            @RequestBody CertificationRequest request) {

        return certificationService.createCertification(request);
    }

    @GetMapping
    public List<CertificationResponse> getAllCertifications() {

        return certificationService.getAllCertifications();
    }

    @GetMapping("/user/{userId}")
    public List<CertificationResponse> getCertificationsByUserId(
            @PathVariable Long userId) {

        return certificationService.getCertificationsByUserId(userId);
    }

    @PutMapping("/{id}")
    public CertificationResponse updateCertification(
            @PathVariable Long id,
            @RequestBody CertificationRequest request) {

        return certificationService.updateCertification(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteCertification(@PathVariable Long id) {
        certificationService.deleteCertification(id);
    }
}