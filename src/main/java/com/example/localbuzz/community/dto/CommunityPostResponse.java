package com.example.localbuzz.community.dto;

import com.example.localbuzz.community.entity.CommunityPostType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommunityPostResponse {

    private Long id;

    private String title;

    private String description;

    private CommunityPostType type;

    private Double latitude;

    private Double longitude;

    private String authorName;

    private LocalDateTime createdAt;
}