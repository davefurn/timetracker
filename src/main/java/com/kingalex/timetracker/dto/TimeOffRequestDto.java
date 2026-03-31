package com.kingalex.timetracker.dto;

import com.kingalex.timetracker.domain.entity.LeaveType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDate;


@Data
public class TimeOffRequestDto {
    @NotNull
    private Long userId;

    @NotNull
    private LeaveType leaveType;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    private String reason;
}
