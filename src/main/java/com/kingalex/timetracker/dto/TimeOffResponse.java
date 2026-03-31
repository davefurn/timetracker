package com.kingalex.timetracker.dto;

import com.kingalex.timetracker.domain.entity.LeaveRequestStatus;
import com.kingalex.timetracker.domain.entity.LeaveType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
public class TimeOffResponse {
    private Long id;
    private Long userId;
    private LeaveType leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private LeaveRequestStatus status;
    private Long reviewedBy;
    private LocalDateTime reviewedAt;
    private LocalDateTime createdAt;
}
