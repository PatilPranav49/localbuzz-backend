package com.example.localbuzz.feed.dto;

import com.example.localbuzz.business.entity.BusinessCategory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NearbyBusinessResponse {

    private Long businessId;
    private String businessName;
    private BusinessCategory category;
    private Double latitude;
    private Double longitude;
    private String latestUpdate;
    private Double distanceKm;
}
