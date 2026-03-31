package com.kingalex.timetracker.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrganizationResponse {
    private Long id;
    private String name;
    private String slug;
    private Boolean isActive;
    private LocalDateTime createdAt;
}
