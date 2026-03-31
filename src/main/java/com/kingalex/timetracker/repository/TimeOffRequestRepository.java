package com.kingalex.timetracker.repository;

import com.kingalex.timetracker.domain.entity.LeaveRequestStatus;
import com.kingalex.timetracker.domain.entity.TimeOffRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeOffRequestRepository extends JpaRepository<TimeOffRequest, Long> {
    List<TimeOffRequest> findByUserId(Long userId);
    List<TimeOffRequest> findByStatus(LeaveRequestStatus status);
    List<TimeOffRequest> findByUserIdAndStatus(
            Long userId, LeaveRequestStatus status
    );
}