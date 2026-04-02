package com.kingalex.timetracker.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ClockOutRequest {
    private UUID userId;
    private String notes;
}
