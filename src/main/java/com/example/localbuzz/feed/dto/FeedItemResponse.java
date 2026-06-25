package com.example.localbuzz.feed.dto;

import com.example.localbuzz.business.entity.BusinessCategory;
import com.example.localbuzz.update.entity.UpdateType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FeedItemResponse {

    private Long updateId;

    private Long businessId;
    private String businessName;

    private BusinessCategory category;

    private String address;
    private String coverImageUrl;

    private UpdateType updateType;

    private String title;
    private String description;

    private LocalDateTime createdAt;
}