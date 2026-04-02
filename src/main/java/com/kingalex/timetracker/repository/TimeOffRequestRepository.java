package com.kingalex.timetracker.repository;

import com.kingalex.timetracker.domain.entity.LeaveRequestStatus;
import com.kingalex.timetracker.domain.entity.TimeOffRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TimeOffRequestRepository extends JpaRepository<TimeOffRequest, UUID> {
    List<TimeOffRequest> findByUserId(UUID userId);
    List<TimeOffRequest> findByStatus(LeaveRequestStatus status);
    List<TimeOffRequest> findByUserIdAndStatus(
            UUID userId, LeaveRequestStatus status
    );
}