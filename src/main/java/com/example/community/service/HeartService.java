package com.example.community.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.community.domain.HeartWallet;
import com.example.community.domain.IdolCandidate;
import com.example.community.dto.HeartVoteRequest;
import com.example.community.dto.HeartVoteResponse;
import com.example.community.dto.HeartWalletCreateRequest;
import com.example.community.dto.HeartWalletResponse;
import com.example.community.exception.CandidateNotFoundException;
import com.example.community.exception.DuplicateHeartWalletException;
import com.example.community.exception.HeartWalletNotFoundException;
import com.example.community.exception.InsufficientHeartsException;
import com.example.community.repository.HeartWalletRepository;
import com.example.community.repository.IdolCandidateRepository;

@Service
@Transactional(readOnly = true)
public class HeartService {

    private final HeartWalletRepository heartWalletRepository;
    private final IdolCandidateRepository candidateRepository;

    public HeartService(
            HeartWalletRepository heartWalletRepository,
            IdolCandidateRepository candidateRepository) {
        this.heartWalletRepository = heartWalletRepository;
        this.candidateRepository = candidateRepository;
    }

    @Transactional
    public HeartWalletResponse createWallet(
            HeartWalletCreateRequest request) {

        if (heartWalletRepository.existsByUserId(request.userId())) {
            throw new DuplicateHeartWalletException(request.userId());
        }

        HeartWallet wallet =
                new HeartWallet(request.userId(), request.balance());

        HeartWallet saved =
                heartWalletRepository.save(wallet);

        return new HeartWalletResponse(
                saved.getUserId(),
                saved.getBalance()
        );
    }

    public HeartWalletResponse findWallet(String userId) {

        HeartWallet wallet = heartWalletRepository
                .findByUserId(userId)
                .orElseThrow(
                        () -> new HeartWalletNotFoundException(userId)
                );

        return new HeartWalletResponse(
                wallet.getUserId(),
                wallet.getBalance()
        );
    }

    @Transactional
    public HeartVoteResponse vote(
            Long candidateId,
            HeartVoteRequest request) {

        if (!heartWalletRepository.existsByUserId(request.userId())) {
            throw new HeartWalletNotFoundException(request.userId());
        }

        int deducted = heartWalletRepository.deduct(
                request.userId(),
                request.hearts()
        );

        if (deducted == 0) {
            throw new InsufficientHeartsException(request.userId());
        }

        int updated = candidateRepository.incrementVoteCountBy(
                candidateId,
                request.hearts()
        );

        if (updated == 0) {
            throw new CandidateNotFoundException(candidateId);
        }

        HeartWallet wallet = heartWalletRepository
                .findByUserId(request.userId())
                .orElseThrow(
                        () -> new HeartWalletNotFoundException(
                                request.userId()
                        )
                );

        IdolCandidate candidate = candidateRepository
                .findById(candidateId)
                .orElseThrow(
                        () -> new CandidateNotFoundException(candidateId)
                );

        return new HeartVoteResponse(
                wallet.getUserId(),
                wallet.getBalance(),
                candidate.getId(),
                candidate.getVoteCount()
        );
    }
}