package com.example.community.dto;

import jakarta.validation.constraints.NotBlank;

public record PostCreateRequest(
		@NotBlank
		String author,
		@NotBlank
		String title,
		String content
) {
}