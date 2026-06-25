package com.example.localbuzz.community.repository;

import com.example.localbuzz.community.entity.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityPostRepository
        extends JpaRepository<CommunityPost, Long> {

    List<CommunityPost>
    findAllByOrderByCreatedAtDesc();
}