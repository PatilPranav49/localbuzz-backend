package com.example.localbuzz.ai.service;

import com.example.localbuzz.ai.dto.BusinessDescriptionRequest;
import com.example.localbuzz.ai.dto.CommunityAnnouncementRequest;
import com.example.localbuzz.ai.dto.PromotionalPostRequest;

public interface AIService {

    String test(String message);

    String generateBusinessDescription(BusinessDescriptionRequest request);

    String generatePromotionalPost(PromotionalPostRequest request);

    String generateCommunityAnnouncement(CommunityAnnouncementRequest request);

}