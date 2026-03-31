package com.kingalex.timetracker.repository;

import com.kingalex.timetracker.domain.entity.ProjectTimeLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProjectTimeLogRepository extends JpaRepository<ProjectTimeLog, Long> {
    List<ProjectTimeLog> findByUserId(Long userId);
    List<ProjectTimeLog> findByProjectId(Long projectId);
    List<ProjectTimeLog> findByAttendanceRecordId(Long attendanceRecordId);
    List<ProjectTimeLog> findByUserIdAndStartAtBetween(
            Long userId, LocalDateTime start, LocalDateTime end
    );
}