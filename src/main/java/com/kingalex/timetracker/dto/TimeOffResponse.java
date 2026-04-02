package com.kingalex.timetracker.dto;

import com.kingalex.timetracker.domain.entity.LeaveRequestStatus;
import com.kingalex.timetracker.domain.entity.LeaveType;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;


@Data
@Builder
public class TimeOffResponse {
    private UUID id;
    private UUID userId;
    private LeaveType leaveType;
    private Instant startDate;
    private Instant endDate;
    private String reason;
    private LeaveRequestStatus status;
    private UUID reviewedBy;
    private Instant reviewedAt;
    private Instant createdAt;
}
