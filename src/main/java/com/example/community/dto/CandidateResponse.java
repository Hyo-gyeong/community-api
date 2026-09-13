package com.example.community.dto;

public record CandidateResponse(
        Long id,
        String name,
        long voteCount
) {
}