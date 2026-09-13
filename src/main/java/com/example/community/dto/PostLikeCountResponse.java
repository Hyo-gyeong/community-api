package com.example.community.dto;

public record PostLikeCountResponse(
        Long postId,
        long count
) {
}