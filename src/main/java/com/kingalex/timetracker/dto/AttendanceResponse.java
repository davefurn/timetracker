package com.kingalex.timetracker.dto;

import com.kingalex.timetracker.domain.entity.AttendanceStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AttendanceResponse {
    private Long id;
    private Long userId;
    private LocalDateTime clockInAt;
    private LocalDateTime clockOutAt;
    private Integer durationMinutes;
    private AttendanceStatus status;
    private String notes;
    private LocalDateTime createdAt;
}
