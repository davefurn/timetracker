package com.kingalex.timetracker.repository;

import com.kingalex.timetracker.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

    List<Project> findByOrganizationId(UUID organizationId);
    List<Project> findByOrganizationIdAndIsActiveTrue(UUID organizationId);
    boolean existsByNameAndOrganizationId(String name, UUID organizationId);
}