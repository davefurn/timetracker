package com.kingalex.timetracker.controller;

import com.kingalex.timetracker.dto.ApiResponse;
import com.kingalex.timetracker.dto.ShiftRequest;
import com.kingalex.timetracker.dto.ShiftResponse;
import com.kingalex.timetracker.service.ShiftService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shifts")
@RequiredArgsConstructor
public class ShiftController {

    private final ShiftService shiftService;

    @PostMapping
    public ResponseEntity<ApiResponse<ShiftResponse>> create(
            @Valid @RequestBody ShiftRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Shift created", shiftService.create(request)));
    }

    @PutMapping("/{id}/publish")
    public ResponseEntity<ApiResponse<ShiftResponse>> publish(
            @PathVariable UUID id) {
        return ResponseEntity.ok(
                ApiResponse.success("Shift published", shiftService.publish(id)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<ShiftResponse>>> getUserShifts(
            @PathVariable UUID userId) {
        return ResponseEntity.ok(
                ApiResponse.success(shiftService.getUserShifts(userId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        shiftService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Shift deleted", null));
    }
}