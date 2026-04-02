package com.kingalex.timetracker.repository;

import com.kingalex.timetracker.domain.entity.UserRole;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {

    List<UserRole> findByUserId(UUID userId);
    List<UserRole> findByRoleId(UUID roleId);
    boolean existsByUserIdAndRoleId(UUID userId, UUID roleId);
    @Transactional
    void deleteByUserIdAndRoleId(UUID userId, UUID roleId);

}