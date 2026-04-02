package com.kingalex.timetracker.repository;

import com.kingalex.timetracker.domain.entity.ProjectTimeLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ProjectTimeLogRepository extends JpaRepository<ProjectTimeLog, UUID> {
    List<ProjectTimeLog> findByUserId(UUID userId);
    List<ProjectTimeLog> findByProjectId(UUID projectId);
    List<ProjectTimeLog> findByAttendanceRecordId(UUID attendanceRecordId);
    List<ProjectTimeLog> findByUserIdAndStartAtBetween(
            UUID userId, LocalDateTime start, LocalDateTime end
    );
}