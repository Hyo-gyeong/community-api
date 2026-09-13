package com.example.community.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record HeartVoteRequest(
        @NotBlank String userId,
        @Positive long hearts
) {
}