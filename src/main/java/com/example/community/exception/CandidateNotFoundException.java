package com.example.community.exception;

public class CandidateNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CandidateNotFoundException(Long id) {
        super("Candidate not found. id=" + id);
    }
}