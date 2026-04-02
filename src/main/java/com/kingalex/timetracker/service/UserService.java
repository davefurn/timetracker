package com.kingalex.timetracker.service;

import com.kingalex.timetracker.domain.entity.Organization;
import com.kingalex.timetracker.domain.entity.Role;
import com.kingalex.timetracker.domain.entity.User;
import com.kingalex.timetracker.domain.entity.UserRole;
import com.kingalex.timetracker.dto.UserRequest;
import com.kingalex.timetracker.dto.UserResponse;
import com.kingalex.timetracker.exception.DuplicateResourceException;
import com.kingalex.timetracker.exception.ResourceNotFoundException;
import com.kingalex.timetracker.repository.OrganizationRepository;
import com.kingalex.timetracker.repository.RoleRepository;
import com.kingalex.timetracker.repository.UserRepository;
import com.kingalex.timetracker.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    public void assignRole(UUID userId, String roleName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId));

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + roleName, userId));

        // Check if already assigned
        if (userRoleRepository.existsByUserIdAndRoleId(userId, role.getId())) {
            throw new ResourceNotFoundException("Role already assigned to user" );
        }

        UserRole userRole = UserRole.builder()
                .user(user)
                .role(role)
                .build();

        userRoleRepository.save(userRole);
    }

    public void removeRole(UUID userId, String roleName) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found for" + roleName, userId));

        userRoleRepository.deleteByUserIdAndRoleId(userId, role.getId());
    }

    public List<String> getUserRoles(UUID userId) {
        return userRoleRepository.findByUserId(userId)
                .stream()
                .map(ur -> ur.getRole().getName())
                .collect(Collectors.toList());
    }
    public UserResponse create(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("User", "email", request.getEmail());
        }
        Organization org = organizationRepository.findBySlug(request.getOrganizationSlug())
                .orElseThrow(() -> new ResourceNotFoundException("Organization"+request.getOrganizationSlug()));

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .organization(org)
                .isActive(true)
                .build();
        return mapToResponse(userRepository.save(user));
    }

    public List<UserResponse> getByOrganization(UUID organizationId) {
        return userRepository.findByOrganizationId(organizationId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getById(UUID id) {
        return mapToResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserResponse update(UUID id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        return mapToResponse(userRepository.save(user));
    }

    public void deactivate(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setIsActive(false);
        userRepository.save(user);
    }
    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    public UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .isActive(user.getIsActive())
                .organizationId(user.getOrganization().getId())
                .createdAt(user.getCreatedAt())
                .build();
    }
}