package com.example.community.dto;

import jakarta.validation.constraints.NotBlank;

public record PostLikeCreateRequest(

    @NotBlank
    String userId
) {
}