package com.nexora.backend.repository;

import com.nexora.backend.model.RequiredSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequiredSkillRepository extends JpaRepository<RequiredSkill, Long> {

    List<RequiredSkill> findByCareerRoleId(Long careerRoleId);
}
