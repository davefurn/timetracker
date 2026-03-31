package com.kingalex.timetracker.service;

import com.kingalex.timetracker.domain.entity.AttendanceRecord;
import com.kingalex.timetracker.domain.entity.AttendanceStatus;
import com.kingalex.timetracker.domain.entity.User;
import com.kingalex.timetracker.dto.AttendanceResponse;
import com.kingalex.timetracker.dto.ClockInRequest;
import com.kingalex.timetracker.dto.ClockOutRequest;
import com.kingalex.timetracker.repository.AttendanceRecordRepository;
import com.kingalex.timetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRecordRepository attendanceRepository;
    private final UserRepository userRepository;

    public AttendanceResponse clockIn(ClockInRequest request) {
        // Check if already clocked in
        attendanceRepository.findFirstByUserIdAndStatus(
                        request.getUserId(), AttendanceStatus.OPEN)
                .ifPresent(r -> {
                    throw new RuntimeException("User is already clocked in");
                });

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        AttendanceRecord record = AttendanceRecord.builder()
                .user(user)
                .clockInAt(LocalDateTime.now())
                .status(AttendanceStatus.OPEN)
                .notes(request.getNotes())
                .build();

        return mapToResponse(attendanceRepository.save(record));
    }

    public AttendanceResponse clockOut(ClockOutRequest request) {
        AttendanceRecord record = attendanceRepository
                .findFirstByUserIdAndStatus(request.getUserId(), AttendanceStatus.OPEN)
                .orElseThrow(() -> new RuntimeException("No active clock-in found"));

        LocalDateTime clockOut = LocalDateTime.now();
        int minutes = (int) ChronoUnit.MINUTES.between(record.getClockInAt(), clockOut);

        record.setClockOutAt(clockOut);
        record.setDurationMinutes(minutes);
        record.setStatus(AttendanceStatus.CLOSED);
        if (request.getNotes() != null) {
            record.setNotes(request.getNotes());
        }

        return mapToResponse(attendanceRepository.save(record));
    }

    public List<AttendanceResponse> getUserAttendance(Long userId) {
        return attendanceRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private AttendanceResponse mapToResponse(AttendanceRecord record) {
        return AttendanceResponse.builder()
                .id(record.getId())
                .userId(record.getUser().getId())
                .clockInAt(record.getClockInAt())
                .clockOutAt(record.getClockOutAt())
                .durationMinutes(record.getDurationMinutes())
                .status(record.getStatus())
                .notes(record.getNotes())
                .createdAt(record.getCreatedAt())
                .build();
    }
}