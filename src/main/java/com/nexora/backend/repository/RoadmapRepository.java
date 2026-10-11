package com.nexora.backend.repository;

import com.nexora.backend.model.Roadmap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoadmapRepository extends JpaRepository<Roadmap, Long> {

    List<Roadmap> findByUserId(Long userId);

    List<Roadmap> findByUserIdOrderByStepNumberAsc(Long userId);

    void deleteByUserId(Long userId);
}