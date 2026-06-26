package com.example.localbuzz.ai.controller;

import com.example.localbuzz.ai.dto.*;
import com.example.localbuzz.ai.service.AIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AIController {

    private final AIService aiService;

    @PostMapping("/business-description")
    public ResponseEntity<AIResponse> generateBusinessDescription(
            @Valid @RequestBody BusinessDescriptionRequest request) {

        return ResponseEntity.ok(
                new AIResponse(aiService.generateBusinessDescription(request))
        );
    }

    @PostMapping("/promotional-post")
    public ResponseEntity<AIResponse> generatePromotionalPost(
            @Valid @RequestBody PromotionalPostRequest request) {

        return ResponseEntity.ok(
                new AIResponse(aiService.generatePromotionalPost(request))
        );
    }

    @PostMapping("/community-post")
    public ResponseEntity<AIResponse> generateCommunityAnnouncement(
            @Valid @RequestBody CommunityAnnouncementRequest request) {

        return ResponseEntity.ok(
                new AIResponse(aiService.generateCommunityAnnouncement(request))
        );
    }
}
