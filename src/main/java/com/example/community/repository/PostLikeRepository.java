package com.example.community.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.community.domain.PostLike;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    boolean existsByPost_IdAndUserId(Long postId, String userId);

    Optional<PostLike> findByPost_IdAndUserId(Long postId, String userId);

    long countByPost_Id(Long postId);
}