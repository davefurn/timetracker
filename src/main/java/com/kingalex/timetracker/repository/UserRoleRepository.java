package com.kingalex.timetracker.repository;

import com.kingalex.timetracker.domain.entity.UserRole;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findByUserId(Long userId);
    List<UserRole> findByRoleId(Long roleId);
    boolean existsByUserIdAndRoleId(Long userId, Long roleId);
    @Transactional
    void deleteByUserIdAndRoleId(Long userId, Long roleId);

}