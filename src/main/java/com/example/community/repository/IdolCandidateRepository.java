package com.example.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.community.domain.IdolCandidate;

public interface IdolCandidateRepository
        extends JpaRepository<IdolCandidate, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
        update IdolCandidate c
        set c.voteCount = c.voteCount + 1
        where c.id = :id
    """)
    int incrementVoteCount(@Param("id") Long id);
}