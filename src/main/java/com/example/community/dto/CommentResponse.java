package com.example.community.dto;

import java.time.LocalDateTime;

public record CommentResponse(
    Long id,
    Long postId,
    String author,
    String content,
    LocalDateTime createdAt
) {
}