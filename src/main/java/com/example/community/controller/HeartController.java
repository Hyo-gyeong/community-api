package com.example.community.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.community.dto.HeartVoteRequest;
import com.example.community.dto.HeartVoteResponse;
import com.example.community.dto.HeartWalletCreateRequest;
import com.example.community.dto.HeartWalletResponse;
import com.example.community.service.HeartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hearts")
public class HeartController {

    private final HeartService heartService;

    public HeartController(HeartService heartService) {
        this.heartService = heartService;
    }

    @PostMapping("/wallets")
    public ResponseEntity<HeartWalletResponse> createWallet(
            @Valid @RequestBody HeartWalletCreateRequest request) {

        return ResponseEntity.ok(
                heartService.createWallet(request)
        );
    }

    @GetMapping("/wallets/{userId}")
    public ResponseEntity<HeartWalletResponse> findWallet(
            @PathVariable String userId) {

        return ResponseEntity.ok(
                heartService.findWallet(userId)
        );
    }

    @PostMapping("/candidates/{candidateId}/votes")
    public ResponseEntity<HeartVoteResponse> vote(
            @PathVariable Long candidateId,
            @Valid @RequestBody HeartVoteRequest request) {

        return ResponseEntity.ok(
                heartService.vote(candidateId, request)
        );
    }
}