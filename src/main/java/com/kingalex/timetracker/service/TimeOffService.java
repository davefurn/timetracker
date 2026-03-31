package com.kingalex.timetracker.service;

import com.kingalex.timetracker.domain.entity.*;
import com.kingalex.timetracker.dto.TimeOffRequestDto;
import com.kingalex.timetracker.dto.TimeOffResponse;
import com.kingalex.timetracker.repository.TimeOffRequestRepository;
import com.kingalex.timetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimeOffService {

    private final TimeOffRequestRepository timeOffRepository;
    private final UserRepository userRepository;

    public TimeOffResponse create(TimeOffRequestDto request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        TimeOffRequest timeOff = TimeOffRequest.builder()
                .user(user)
                .leaveType(request.getLeaveType())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .reason(request.getReason())
                .status(LeaveRequestStatus.PENDING)
                .build();

        return mapToResponse(timeOffRepository.save(timeOff));
    }

    public TimeOffResponse approve(Long id, Long reviewerId) {
        TimeOffRequest request = timeOffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        User reviewer = userRepository.findById(reviewerId)
                .orElseThrow(() -> new RuntimeException("Reviewer not found"));

        request.setStatus(LeaveRequestStatus.APPROVED);
        request.setReviewedBy(reviewer);
        request.setReviewedAt(LocalDateTime.now());
        return mapToResponse(timeOffRepository.save(request));
    }

    public TimeOffResponse reject(Long id, Long reviewerId) {
        TimeOffRequest request = timeOffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        User reviewer = userRepository.findById(reviewerId)
                .orElseThrow(() -> new RuntimeException("Reviewer not found"));

        request.setStatus(LeaveRequestStatus.REJECTED);
        request.setReviewedBy(reviewer);
        request.setReviewedAt(LocalDateTime.now());
        return mapToResponse(timeOffRepository.save(request));
    }

    public List<TimeOffResponse> getByUser(Long userId) {
        return timeOffRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<TimeOffResponse> getPending() {
        return timeOffRepository.findByStatus(LeaveRequestStatus.PENDING)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private TimeOffResponse mapToResponse(TimeOffRequest r) {
        return TimeOffResponse.builder()
                .id(r.getId())
                .userId(r.getUser().getId())
                .leaveType(r.getLeaveType())
                .startDate(r.getStartDate())
                .endDate(r.getEndDate())
                .reason(r.getReason())
                .status(r.getStatus())
                .reviewedBy(r.getReviewedBy() != null ? r.getReviewedBy().getId() : null)
                .reviewedAt(r.getReviewedAt())
                .createdAt(r.getCreatedAt())
                .build();
    }
}