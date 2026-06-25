package com.example.localbuzz.community.dto;

import com.example.localbuzz.community.entity.CommunityPostType;
import lombok.Data;

@Data
public class CommunityPostRequest {

    private String title;

    private String description;

    private CommunityPostType type;

    private Double latitude;

    private Double longitude;
}