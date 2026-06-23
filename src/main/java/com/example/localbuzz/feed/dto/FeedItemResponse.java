package com.example.localbuzz.feed.dto;

import com.example.localbuzz.update.entity.UpdateType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FeedItemResponse {

    private Long updateId;
    private String businessName;
    private UpdateType updateType;
    private String title;
    private String description;
    private LocalDateTime createdAt;
}