package com.example.localbuzz.feed.service;

import com.example.localbuzz.feed.dto.FeedItemResponse;
import com.example.localbuzz.feed.dto.NearbyBusinessResponse;

import java.util.List;

public interface FeedService {

    List<FeedItemResponse> getFeed(
            String category,
            String type
    );

    List<NearbyBusinessResponse> getNearbyBusinesses(
            Double latitude,
            Double longitude,
            Double radiusKm
    );
}