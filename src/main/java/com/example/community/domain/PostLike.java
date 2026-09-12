package com.example.community.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "post_likes",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_post_likes_post_user",
            columnNames = {"post_id", "user_id"}
        )
    }
)
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    protected PostLike() {
    }

    public PostLike(Post post, String userId) {
        this.post = post;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}