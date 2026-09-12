package com.example.community.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentCreateRequest(

    @NotBlank
    String author,

    @NotBlank
    String content
) {
}