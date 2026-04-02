package com.kingalex.timetracker.dto;

import com.kingalex.timetracker.domain.entity.NotificationType;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;


@Data
@Builder


public class NotificationResponse {
    private UUID id;
    private UUID userId;
    private NotificationType type;
    private String title;
    private String message;
    private Boolean isRead;
    private Instant createdAt;
}
