package com.kingalex.timetracker.repository;

import com.kingalex.timetracker.domain.entity.Shift;
import com.kingalex.timetracker.domain.entity.ShiftStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ShiftRepository extends JpaRepository<Shift, UUID> {


    List<Shift> findByUserId(UUID userId);
    List<Shift> findByUserIdAndStatus(UUID userId, ShiftStatus status);
    List<Shift> findByUserIdAndStartTimeBetween(
            UUID userId, LocalDateTime start, LocalDateTime end
    );
    List<Shift> findByIsPublishedTrue();
}