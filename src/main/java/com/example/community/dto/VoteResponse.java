package com.example.community.dto;

public record VoteResponse(
        Long candidateId,
        long voteCount
) {
}