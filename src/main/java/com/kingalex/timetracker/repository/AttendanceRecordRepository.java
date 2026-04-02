package com.kingalex.timetracker.repository;

import com.kingalex.timetracker.domain.entity.AttendanceRecord;
import com.kingalex.timetracker.domain.entity.AttendanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, UUID> {
    List<AttendanceRecord> findByUserId(UUID userId);
    List<AttendanceRecord> findByUserIdAndStatus(Long userId, AttendanceStatus status);
    List<AttendanceRecord> findByUserIdAndClockInAtBetween(
            UUID userId, LocalDateTime start, LocalDateTime end
    );
    // Find open record - employee is currently clocked in
    Optional<AttendanceRecord> findFirstByUserIdAndStatus(
            UUID userId, AttendanceStatus status
    );

}