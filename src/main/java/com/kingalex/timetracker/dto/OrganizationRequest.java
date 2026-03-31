package com.kingalex.timetracker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class OrganizationRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Slug is required")
    private String slug;
}