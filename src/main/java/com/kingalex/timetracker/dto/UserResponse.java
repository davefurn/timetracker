package com.kingalex.timetracker.dto;

import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class UserResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Boolean isActive;
    private UUID organizationId;
    private Instant createdAt;
}
