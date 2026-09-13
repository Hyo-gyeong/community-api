package com.example.community.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.community.domain.IdolCandidate;
import com.example.community.dto.CandidateCreateRequest;
import com.example.community.dto.CandidateRankingResponse;
import com.example.community.dto.CandidateResponse;
import com.example.community.dto.VoteResponse;
import com.example.community.exception.CandidateNotFoundException;
import com.example.community.repository.IdolCandidateRepository;

@Service
@Transactional(readOnly = true)
public class IdolCandidateService {

    private final IdolCandidateRepository candidateRepository;

    public IdolCandidateService(
            IdolCandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Transactional
    public CandidateResponse create(CandidateCreateRequest request) {

        IdolCandidate candidate =
                new IdolCandidate(request.name());

        IdolCandidate saved =
                candidateRepository.save(candidate);

        return toResponse(saved);
    }

    public CandidateResponse findById(Long id) {

        IdolCandidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException(id));

        return toResponse(candidate);
    }

    @Transactional
    public VoteResponse vote(Long id) {

        int updatedRows =
                candidateRepository.incrementVoteCount(id);

        if (updatedRows == 0) {
            throw new CandidateNotFoundException(id);
        }

        IdolCandidate candidate =
                candidateRepository.findById(id)
                        .orElseThrow(
                            () -> new CandidateNotFoundException(id)
                        );

        return new VoteResponse(
                candidate.getId(),
                candidate.getVoteCount()
        );
    }
    
    public Page<CandidateRankingResponse> findRanking(
            int page,
            int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(
                        Sort.Order.desc("voteCount"),
                        Sort.Order.asc("id")
                )
        );

        Page<IdolCandidate> candidates =
                candidateRepository.findAll(pageable);

        int startRank = page * size + 1;

        java.util.concurrent.atomic.AtomicInteger rank =
                new java.util.concurrent.atomic.AtomicInteger(startRank);

        return candidates.map(candidate ->
                new CandidateRankingResponse(
                        rank.getAndIncrement(),
                        candidate.getId(),
                        candidate.getName(),
                        candidate.getVoteCount()
                )
        );
    }

    private CandidateResponse toResponse(IdolCandidate candidate) {
        return new CandidateResponse(
                candidate.getId(),
                candidate.getName(),
                candidate.getVoteCount()
        );
    }
}