package com.example.community.dto;

public record HeartVoteResponse(
        String userId,
        long remainingHearts,
        Long candidateId,
        long voteCount
) {
}