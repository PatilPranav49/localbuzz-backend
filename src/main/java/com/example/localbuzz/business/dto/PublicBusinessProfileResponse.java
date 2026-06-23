package com.example.localbuzz.business.dto;

import com.example.localbuzz.business.entity.BusinessCategory;
import com.example.localbuzz.business.entity.BusinessStatus;
import com.example.localbuzz.feed.dto.FeedItemResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PublicBusinessProfileResponse {

    private Long id;
    private String name;
    private String description;
    private String address;

    private Double latitude;
    private Double longitude;

    private String phone;
    private String website;
    private String coverImageUrl;

    private BusinessCategory category;
    private BusinessStatus status;

    private List<FeedItemResponse> recentUpdates;
}