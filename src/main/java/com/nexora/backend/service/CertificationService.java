package com.nexora.backend.service;

import com.nexora.backend.dto.CertificationRequest;
import com.nexora.backend.dto.CertificationResponse;
import com.nexora.backend.model.Certification;
import com.nexora.backend.model.User;
import com.nexora.backend.repository.CertificationRepository;
import com.nexora.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificationService {

    private final CertificationRepository certificationRepository;
    private final UserRepository userRepository;

    public CertificationService(
            CertificationRepository certificationRepository,
            UserRepository userRepository) {

        this.certificationRepository = certificationRepository;
        this.userRepository = userRepository;
    }

    public CertificationResponse createCertification(
            CertificationRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Certification certification = new Certification(
                request.getName(),
                request.getIssuer(),
                request.getIssueDate(),
                request.getCredentialUrl(),
                user
        );

        Certification savedCertification =
                certificationRepository.save(certification);

        return convertToResponse(savedCertification);
    }

    public List<CertificationResponse> getAllCertifications() {

        return certificationRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public List<CertificationResponse> getCertificationsByUserId(
            Long userId) {

        return certificationRepository.findByUserId(userId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public CertificationResponse updateCertification(
            Long id,
            CertificationRequest request) {

        Certification cert = certificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certification not found"));

        if (request.getName() != null) cert.setName(request.getName());
        if (request.getIssuer() != null) cert.setIssuer(request.getIssuer());
        if (request.getIssueDate() != null) cert.setIssueDate(request.getIssueDate());
        if (request.getCredentialUrl() != null) cert.setCredentialUrl(request.getCredentialUrl());

        Certification updated = certificationRepository.save(cert);
        return convertToResponse(updated);
    }

    public void deleteCertification(Long id) {
        Certification cert = certificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certification not found"));
        certificationRepository.delete(cert);
    }

    private CertificationResponse convertToResponse(Certification cert) {
        return new CertificationResponse(
                cert.getId(),
                cert.getName(),
                cert.getIssuer(),
                cert.getIssueDate(),
                cert.getCredentialUrl(),
                cert.getUser() != null ? cert.getUser().getId() : null
        );
    }
}
