package com.kingalex.timetracker.controller;

import com.kingalex.timetracker.dto.ApiResponse;
import com.kingalex.timetracker.dto.OrganizationRequest;
import com.kingalex.timetracker.dto.OrganizationResponse;
import com.kingalex.timetracker.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<ApiResponse<OrganizationResponse>> create(
            @Valid @RequestBody OrganizationRequest request) {
        OrganizationResponse response = organizationService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Organization created", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrganizationResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(organizationService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrganizationResponse>> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(organizationService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OrganizationResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody OrganizationRequest request) {
        return ResponseEntity.ok(
                ApiResponse.success("Organization updated", organizationService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        organizationService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Organization deleted", null));
    }
}