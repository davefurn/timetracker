package com.kingalex.timetracker.repository;

import com.kingalex.timetracker.domain.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrganizationRepository extends JpaRepository<Organization, UUID> {


    Optional<Organization> findBySlug(String slug);

    java.util.List<Organization> findByIsActiveTrue();

    boolean existsBySlug(String slug);
}