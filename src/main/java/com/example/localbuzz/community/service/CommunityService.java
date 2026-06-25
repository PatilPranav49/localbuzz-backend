package com.example.localbuzz.community.service;

import com.example.localbuzz.community.dto.CommunityPostRequest;
import com.example.localbuzz.community.dto.CommunityPostResponse;

import java.util.List;

public interface CommunityService {

    CommunityPostResponse createPost(
            CommunityPostRequest request,
            Long userId
    );

    List<CommunityPostResponse> getPosts(
            Double latitude,
            Double longitude,
            Double radiusKm
    );

    void deletePost(
            Long postId,
            Long userId
    );
}