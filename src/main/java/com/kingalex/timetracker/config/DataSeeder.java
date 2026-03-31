package com.kingalex.timetracker.config;

import com.kingalex.timetracker.domain.entity.Role;
import com.kingalex.timetracker.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        seedRole("ADMIN", "Full system access");
        seedRole("MANAGER", "Manage team schedules and approvals");
        seedRole("EMPLOYEE", "Basic employee access");
    }

    private void seedRole(String name, String description) {
        if (!roleRepository.existsByName(name)) {
            roleRepository.save(Role.builder()
                    .name(name)
                    .description(description)
                    .build());
        }
    }
}