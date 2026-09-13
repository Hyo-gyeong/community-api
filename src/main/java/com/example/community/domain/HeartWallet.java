package com.example.community.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "heart_wallets",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_heart_wallet_user",
            columnNames = "user_id"
        )
    }
)
public class HeartWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false)
    private long balance;

    protected HeartWallet() {
    }

    public HeartWallet(String userId, long balance) {
        this.userId = userId;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public long getBalance() {
        return balance;
    }
}