package com.example.community.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.community.dto.CandidateCreateRequest;
import com.example.community.dto.CandidateRankingResponse;
import com.example.community.dto.CandidateResponse;
import com.example.community.dto.VoteResponse;
import com.example.community.service.IdolCandidateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidates")
public class IdolCandidateController {

    private final IdolCandidateService candidateService;

    public IdolCandidateController(
            IdolCandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping
    public ResponseEntity<CandidateResponse> create(
            @Valid @RequestBody CandidateCreateRequest request,
            UriComponentsBuilder uriBuilder) {

        CandidateResponse response =
                candidateService.create(request);

        URI location = uriBuilder
                .path("/candidates/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponse> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                candidateService.findById(id)
        );
    }

    @PostMapping("/{id}/votes")
    public ResponseEntity<VoteResponse> vote(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                candidateService.vote(id)
        );
    }
    
    @GetMapping("/rankings")
    public ResponseEntity<Page<CandidateRankingResponse>> findRanking(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<CandidateRankingResponse> response =
                candidateService.findRanking(page, size);

        return ResponseEntity.ok(response);
    }
}