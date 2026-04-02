package com.kingalex.timetracker.repository;

import com.kingalex.timetracker.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);
    List<User> findByOrganizationId(UUID organizationId);
    List<User> findByOrganizationIdAndIsActiveTrue(UUID organizationId);
    Optional<User> findByPasswordResetToken(String token);
    Optional<User> findByEmailVerificationToken(String token);
    boolean existsByEmail(String email);
}