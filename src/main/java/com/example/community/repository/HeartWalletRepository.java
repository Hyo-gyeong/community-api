package com.example.community.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.community.domain.HeartWallet;

public interface HeartWalletRepository
        extends JpaRepository<HeartWallet, Long> {

    Optional<HeartWallet> findByUserId(String userId);

    boolean existsByUserId(String userId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
        update HeartWallet w
        set w.balance = w.balance - :amount
        where w.userId = :userId
          and w.balance >= :amount
    """)
    int deduct(
            @Param("userId") String userId,
            @Param("amount") long amount
    );
}