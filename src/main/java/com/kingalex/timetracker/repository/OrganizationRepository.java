package com.kingalex.timetracker.repository;

import com.kingalex.timetracker.domain.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {


    Optional<Organization> findBySlug(String slug);

    java.util.List<Organization> findByIsActiveTrue();

    boolean existsBySlug(String slug);
}