package com.kingalex.timetracker.service;

import com.kingalex.timetracker.domain.entity.Organization;
import com.kingalex.timetracker.dto.OrganizationRequest;
import com.kingalex.timetracker.dto.OrganizationResponse;
import com.kingalex.timetracker.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationResponse create(OrganizationRequest request) {
        if (organizationRepository.existsBySlug(request.getSlug())) {
            throw new RuntimeException("Slug already exists: " + request.getSlug());
        }
        Organization org = Organization.builder()
                .name(request.getName())
                .slug(request.getSlug())
                .isActive(true)
                .build();
        return mapToResponse(organizationRepository.save(org));
    }

    public List<OrganizationResponse> getAll() {
        return organizationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public OrganizationResponse getById(Long id) {
        Organization org = organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found"));
        return mapToResponse(org);
    }

    public OrganizationResponse update(Long id, OrganizationRequest request) {
        Organization org = organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization not found"));
        org.setName(request.getName());
        org.setSlug(request.getSlug());
        return mapToResponse(organizationRepository.save(org));
    }

    public void delete(Long id) {
        organizationRepository.deleteById(id);
    }

    private OrganizationResponse mapToResponse(Organization org) {
        return OrganizationResponse.builder()
                .id(org.getId())
                .name(org.getName())
                .slug(org.getSlug())
                .isActive(org.getIsActive())
                .createdAt(org.getCreatedAt())
                .build();
    }
}