package com.example.community.dto;

public record CandidateRankingResponse(
        int rank,
        Long id,
        String name,
        long voteCount
) {
}