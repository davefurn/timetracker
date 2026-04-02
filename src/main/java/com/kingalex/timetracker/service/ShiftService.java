package com.kingalex.timetracker.service;

import com.kingalex.timetracker.domain.entity.Shift;
import com.kingalex.timetracker.domain.entity.ShiftStatus;
import com.kingalex.timetracker.domain.entity.User;
import com.kingalex.timetracker.dto.ShiftRequest;
import com.kingalex.timetracker.dto.ShiftResponse;
import com.kingalex.timetracker.exception.ResourceNotFoundException;
import com.kingalex.timetracker.repository.ShiftRepository;
import com.kingalex.timetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShiftService {

    private final ShiftRepository shiftRepository;
    private final UserRepository userRepository;

    public ShiftResponse create(ShiftRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->new ResourceNotFoundException("User", request.getUserId()));

        Shift shift = Shift.builder()
                .user(user)
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .status(ShiftStatus.DRAFT)
                .isPublished(false)
                .build();

        return mapToResponse(shiftRepository.save(shift));
    }

    public ShiftResponse publish(UUID shiftId) {
        Shift shift = shiftRepository.findById(shiftId)
                .orElseThrow(() -> new ResourceNotFoundException("Shift", shiftId));
        shift.setStatus(ShiftStatus.PUBLISHED);
        shift.setIsPublished(true);
        shift.setPublishedAt(Instant.now());
        return mapToResponse(shiftRepository.save(shift));
    }

    public List<ShiftResponse> getUserShifts(UUID userId) {
        return shiftRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public void delete(UUID id) {
        shiftRepository.deleteById(id);
    }

    private ShiftResponse mapToResponse(Shift shift) {
        return ShiftResponse.builder()
                .id(shift.getId())
                .userId(shift.getUser().getId())
                .startTime(shift.getStartTime())
                .endTime(shift.getEndTime())
                .status(shift.getStatus())
                .isPublished(shift.getIsPublished())
                .createdAt(shift.getCreatedAt())
                .build();
    }
}