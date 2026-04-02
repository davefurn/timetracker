package com.kingalex.timetracker.controller;

import com.kingalex.timetracker.dto.ApiResponse;
import com.kingalex.timetracker.dto.TimeOffRequestDto;
import com.kingalex.timetracker.dto.TimeOffResponse;
import com.kingalex.timetracker.service.TimeOffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/time-off")
@RequiredArgsConstructor
public class TimeOffController {

    private final TimeOffService timeOffService;

    @PostMapping
    public ResponseEntity<ApiResponse<TimeOffResponse>> create(
            @Valid @RequestBody TimeOffRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Request submitted", timeOffService.create(request)));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<ApiResponse<TimeOffResponse>> approve(
            @PathVariable UUID id,
            @RequestParam UUID reviewerId) {
        return ResponseEntity.ok(
                ApiResponse.success("Request approved", timeOffService.approve(id, reviewerId)));
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<ApiResponse<TimeOffResponse>> reject(
            @PathVariable UUID id,
            @RequestParam UUID reviewerId) {
        return ResponseEntity.ok(
                ApiResponse.success("Request rejected", timeOffService.reject(id, reviewerId)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<TimeOffResponse>>> getByUser(
            @PathVariable UUID userId) {
        return ResponseEntity.ok(ApiResponse.success(timeOffService.getByUser(userId)));
    }

    @GetMapping("/pending")
    public ResponseEntity<ApiResponse<List<TimeOffResponse>>> getPending() {
        return ResponseEntity.ok(ApiResponse.success(timeOffService.getPending()));
    }
}