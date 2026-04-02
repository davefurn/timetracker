package com.kingalex.timetracker.dto;

import com.kingalex.timetracker.domain.entity.LeaveType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.Instant;
import java.util.UUID;


@Data
public class TimeOffRequestDto {
    @NotNull
    private UUID userId;

    @NotNull
    private LeaveType leaveType;

    @NotNull
    private Instant startDate;

    @NotNull
    private Instant endDate;

    private String reason;
}
