package com.kingalex.timetracker.repository;

import com.kingalex.timetracker.domain.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AuditLogRepository extends JpaRepository<AuditLog, UUID> {
    List<AuditLog> findByOrganizationId(UUID organizationId);
    List<AuditLog> findByActorUserId(UUID userId);
    List<AuditLog> findByOrganizationIdAndCreatedAtBetween(
            UUID organizationId, LocalDateTime start, LocalDateTime end
    );
    List<AuditLog> findByEntityTypeAndEntityId(
            String entityType, UUID entityId
    );
}