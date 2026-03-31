package com.kingalex.timetracker.service;

import com.kingalex.timetracker.domain.entity.Organization;
import com.kingalex.timetracker.domain.entity.Role;
import com.kingalex.timetracker.domain.entity.User;
import com.kingalex.timetracker.domain.entity.UserRole;
import com.kingalex.timetracker.dto.UserRequest;
import com.kingalex.timetracker.dto.UserResponse;
import com.kingalex.timetracker.repository.OrganizationRepository;
import com.kingalex.timetracker.repository.RoleRepository;
import com.kingalex.timetracker.repository.UserRepository;
import com.kingalex.timetracker.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    public void assignRole(Long userId, String roleName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));

        // Check if already assigned
        if (userRoleRepository.existsByUserIdAndRoleId(userId, role.getId())) {
            throw new RuntimeException("Role already assigned to user");
        }

        UserRole userRole = UserRole.builder()
                .user(user)
                .role(role)
                .build();

        userRoleRepository.save(userRole);
    }

    public void removeRole(Long userId, String roleName) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        userRoleRepository.deleteByUserIdAndRoleId(userId, role.getId());
    }

    public List<String> getUserRoles(Long userId) {
        return userRoleRepository.findByUserId(userId)
                .stream()
                .map(ur -> ur.getRole().getName())
                .collect(Collectors.toList());
    }
    public UserResponse create(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        Organization org = organizationRepository.findById(request.getOrganizationId())
                .orElseThrow(() -> new RuntimeException("Organization not found"));

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

    public List<UserResponse> getByOrganization(Long organizationId) {
        return userRepository.findByOrganizationId(organizationId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getById(Long id) {
        return mapToResponse(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public UserResponse update(Long id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        return mapToResponse(userRepository.save(user));
    }

    public void deactivate(Long id) {
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