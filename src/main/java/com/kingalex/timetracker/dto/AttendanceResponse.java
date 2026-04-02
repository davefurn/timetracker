package com.kingalex.timetracker.dto;

import com.kingalex.timetracker.domain.entity.AttendanceStatus;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class AttendanceResponse {
    private UUID id;
    private UUID userId;
    private Instant clockInAt;
    private Instant clockOutAt;
    private Integer durationMinutes;
    private AttendanceStatus status;
    private String notes;
    private Instant createdAt;
}
