package com.kingalex.timetracker.dto;

import com.kingalex.timetracker.domain.entity.ShiftStatus;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.UUID;


@Data
@Builder
public class ShiftResponse {
    private UUID id;
    private UUID userId;
    private Instant startTime;
    private Instant endTime;
    private ShiftStatus status;
    private Boolean isPublished;
    private Instant createdAt;
}
