package com.kingalex.timetracker.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.Instant;
import java.util.UUID;


@Data
public class ShiftRequest {
    @NotNull(message = "User ID is required")
    private UUID userId;

    @NotNull(message = "Start time is required")
    private Instant startTime;

    @NotNull(message = "End time is required")
    private Instant endTime;
}
