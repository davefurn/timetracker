package com.kingalex.timetracker.dto;

import com.kingalex.timetracker.domain.entity.NotificationType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;




@Data
@Builder


public class NotificationResponse {
    private Long id;
    private Long userId;
    private NotificationType type;
    private String title;
    private String message;
    private Boolean isRead;
    private LocalDateTime createdAt;
}
