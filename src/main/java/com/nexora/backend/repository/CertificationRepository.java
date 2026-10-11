package com.nexora.backend.repository;

import com.nexora.backend.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificationRepository extends JpaRepository<Certification, Long> {

    List<Certification> findByUserId(Long userId);
}
