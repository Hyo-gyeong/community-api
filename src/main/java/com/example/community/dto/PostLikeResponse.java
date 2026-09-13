package com.example.community.dto;

import java.time.LocalDateTime;

public record PostLikeResponse(
    Long id,
    Long postId,
    String userId,
    LocalDateTime createdAt
) {
}