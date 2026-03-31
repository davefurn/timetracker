package com.kingalex.timetracker.repository;

import com.kingalex.timetracker.domain.entity.Shift;
import com.kingalex.timetracker.domain.entity.ShiftStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShiftRepository extends JpaRepository<Shift, Long> {


    List<Shift> findByUserId(Long userId);
    List<Shift> findByUserIdAndStatus(Long userId, ShiftStatus status);
    List<Shift> findByUserIdAndStartTimeBetween(
            Long userId, LocalDateTime start, LocalDateTime end
    );
    List<Shift> findByIsPublishedTrue();
}