package com.kingalex.timetracker.repository;

import com.kingalex.timetracker.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByOrganizationId(Long organizationId);
    List<Project> findByOrganizationIdAndIsActiveTrue(Long organizationId);
    boolean existsByNameAndOrganizationId(String name, Long organizationId);
}