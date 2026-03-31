package com.kingalex.timetracker.repository;

import com.kingalex.timetracker.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    List<User> findByOrganizationId(Long organizationId);
    List<User> findByOrganizationIdAndIsActiveTrue(Long organizationId);
    Optional<User> findByPasswordResetToken(String token);
    Optional<User> findByEmailVerificationToken(String token);
    boolean existsByEmail(String email);
}