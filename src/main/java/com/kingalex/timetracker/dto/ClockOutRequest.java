package com.kingalex.timetracker.dto;

import lombok.Data;

@Data
public class ClockOutRequest {
    private Long userId;
    private String notes;
}
