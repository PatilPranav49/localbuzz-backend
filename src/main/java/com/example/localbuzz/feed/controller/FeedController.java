package com.example.localbuzz.feed.controller;

import com.example.localbuzz.feed.dto.FeedItemResponse;
import com.example.localbuzz.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.localbuzz.feed.dto.NearbyBusinessResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @GetMapping("/feed")
    public List<FeedItemResponse> getFeed(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String type
    ) {
        return feedService.getFeed(
                category,
                type
        );
    }

    @GetMapping("/feed/nearby")
    public List<NearbyBusinessResponse> getNearbyBusinesses(
            @RequestParam Double lat,
            @RequestParam Double lng,
            @RequestParam Double radius
    ) {
        return feedService.getNearbyBusinesses(
                lat,
                lng,
                radius
        );
    }
}