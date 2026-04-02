package com.kingalex.timetracker.controller;

import com.kingalex.timetracker.dto.ApiResponse;
import com.kingalex.timetracker.dto.AttendanceResponse;
import com.kingalex.timetracker.dto.ClockInRequest;
import com.kingalex.timetracker.dto.ClockOutRequest;
import com.kingalex.timetracker.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/clock-in")
    public ResponseEntity<ApiResponse<AttendanceResponse>> clockIn(
            @RequestBody ClockInRequest request) {
        return ResponseEntity.ok(
                ApiResponse.success("Clocked in successfully",
                        attendanceService.clockIn(request)));
    }

    @PostMapping("/clock-out")
    public ResponseEntity<ApiResponse<AttendanceResponse>> clockOut(
            @RequestBody ClockOutRequest request) {
        return ResponseEntity.ok(
                ApiResponse.success("Clocked out successfully",
                        attendanceService.clockOut(request)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<AttendanceResponse>>> getUserAttendance(
            @PathVariable UUID userId) {
        return ResponseEntity.ok(
                ApiResponse.success(attendanceService.getUserAttendance(userId)));
    }
}