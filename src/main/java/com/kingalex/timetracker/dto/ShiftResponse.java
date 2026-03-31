package com.kingalex.timetracker.dto;

import com.kingalex.timetracker.domain.entity.ShiftStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class ShiftResponse {
    private Long id;
    private Long userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ShiftStatus status;
    private Boolean isPublished;
    private LocalDateTime createdAt;
}
