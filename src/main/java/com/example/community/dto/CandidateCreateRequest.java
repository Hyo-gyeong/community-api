package com.example.community.dto;

import jakarta.validation.constraints.NotBlank;

public record CandidateCreateRequest(
        @NotBlank String name
) {
}