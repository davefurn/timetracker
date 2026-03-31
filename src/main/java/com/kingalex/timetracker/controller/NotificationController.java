package com.kingalex.timetracker.controller;

import com.kingalex.timetracker.dto.ApiResponse;
import com.kingalex.timetracker.dto.NotificationResponse;
import com.kingalex.timetracker.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<NotificationResponse>>> getAll(
            @PathVariable Long userId) {
        return ResponseEntity.ok(
                ApiResponse.success(notificationService.getAll(userId)));
    }

    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<ApiResponse<List<NotificationResponse>>> getUnread(
            @PathVariable Long userId) {
        return ResponseEntity.ok(
                ApiResponse.success(notificationService.getUnread(userId)));
    }

    @GetMapping("/user/{userId}/unread/count")
    public ResponseEntity<ApiResponse<Long>> countUnread(
            @PathVariable Long userId) {
        return ResponseEntity.ok(
                ApiResponse.success(notificationService.countUnread(userId)));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<ApiResponse<Void>> markAsRead(
            @PathVariable Long id) {
        notificationService.markAsRead(id);
        return ResponseEntity.ok(ApiResponse.success("Marked as read", null));
    }
}