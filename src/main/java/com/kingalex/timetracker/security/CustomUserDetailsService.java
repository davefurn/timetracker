package com.kingalex.timetracker.security;

import com.kingalex.timetracker.domain.entity.User;
import com.kingalex.timetracker.domain.entity.UserRole;
import com.kingalex.timetracker.repository.UserRepository;
import com.kingalex.timetracker.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found: " + email));

        // Load actual roles from database
        List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());

        List<SimpleGrantedAuthority> authorities = userRoles.stream()
                .map(ur -> new SimpleGrantedAuthority(
                        "ROLE_" + ur.getRole().getName()))
                .collect(Collectors.toList());

        // If no roles assigned yet default to EMPLOYEE
        if (authorities.isEmpty()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPasswordHash(),
                authorities);
    }
}