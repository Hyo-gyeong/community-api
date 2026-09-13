package com.example.community.dto;

public record HeartWalletResponse(
        String userId,
        long balance
) {
}