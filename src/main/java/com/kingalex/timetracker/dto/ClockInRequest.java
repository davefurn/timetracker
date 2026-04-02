package com.kingalex.timetracker.dto;

import lombok.Data;

import java.util.UUID;


@Data
public class ClockInRequest {
    private UUID userId;
    private String notes;
}
