package com.example.community.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "idol_candidates")
public class IdolCandidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long voteCount;

    protected IdolCandidate() {
    }

    public IdolCandidate(String name) {
        this.name = name;
        this.voteCount = 0L;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getVoteCount() {
        return voteCount;
    }
}