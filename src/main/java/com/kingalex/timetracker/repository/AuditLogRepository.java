package com.kingalex.timetracker.repository;

import com.kingalex.timetracker.domain.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByOrganizationId(Long organizationId);
    List<AuditLog> findByActorUserId(Long userId);
    List<AuditLog> findByOrganizationIdAndCreatedAtBetween(
            Long organizationId, LocalDateTime start, LocalDateTime end
    );
    List<AuditLog> findByEntityTypeAndEntityId(
            String entityType, Long entityId
    );
}