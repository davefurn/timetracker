package com.kingalex.timetracker.controller;

import com.kingalex.timetracker.dto.ApiResponse;
import com.kingalex.timetracker.dto.UserRequest;
import com.kingalex.timetracker.dto.UserResponse;
import com.kingalex.timetracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> create(
            @Valid @RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("User created", userService.create(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(userService.getById(id)));
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getByOrganization(
            @PathVariable Long organizationId) {
        return ResponseEntity.ok(
                ApiResponse.success(userService.getByOrganization(organizationId)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(
                ApiResponse.success("User updated", userService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deactivate(@PathVariable Long id) {
        userService.deactivate(id);
        return ResponseEntity.ok(ApiResponse.success("User deactivated", null));
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAll() {
        return ResponseEntity.ok(
                ApiResponse.success(userService.getAll()));
    }
    @PostMapping("/{userId}/roles/{roleName}")
    public ResponseEntity<ApiResponse<Void>> assignRole(
            @PathVariable Long userId,
            @PathVariable String roleName) {
        userService.assignRole(userId, roleName);
        return ResponseEntity.ok(
                ApiResponse.success("Role assigned successfully", null));
    }

    @DeleteMapping("/{userId}/roles/{roleName}")
    public ResponseEntity<ApiResponse<Void>> removeRole(
            @PathVariable Long userId,
            @PathVariable String roleName) {
        userService.removeRole(userId, roleName);
        return ResponseEntity.ok(
                ApiResponse.success("Role removed successfully", null));
    }

    @GetMapping("/{userId}/roles")
    public ResponseEntity<ApiResponse<List<String>>> getUserRoles(
            @PathVariable Long userId) {
        return ResponseEntity.ok(
                ApiResponse.success(userService.getUserRoles(userId)));
    }

}