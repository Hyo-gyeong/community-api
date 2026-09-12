package com.example.community.dto;

import java.time.LocalDateTime;

public record PostResponse(
		Long id,
		String author,
		String title,
		String content,
		LocalDateTime createdAt
) {
}