package com.kingalex.timetracker.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDateTime;


@Data
public class ShiftRequest {
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Start time is required")
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    private LocalDateTime endTime;
}
