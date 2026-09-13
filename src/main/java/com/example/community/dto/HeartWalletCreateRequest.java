package com.example.community.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record HeartWalletCreateRequest(
        @NotBlank String userId,
        @PositiveOrZero long balance
) {
}