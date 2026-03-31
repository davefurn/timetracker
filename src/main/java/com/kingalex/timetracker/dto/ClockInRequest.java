package com.kingalex.timetracker.dto;

import lombok.Data;


@Data
public class ClockInRequest {
    private Long userId;
    private String notes;
}
