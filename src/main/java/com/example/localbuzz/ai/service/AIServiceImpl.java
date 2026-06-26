package com.example.localbuzz.ai.service;

import com.example.localbuzz.ai.dto.BusinessDescriptionRequest;
import com.example.localbuzz.ai.dto.CommunityAnnouncementRequest;
import com.example.localbuzz.ai.dto.PromotionalPostRequest;
import com.example.localbuzz.ai.exception.AIServiceException;
import com.example.localbuzz.ai.prompt.PromptTemplates;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AIServiceImpl implements AIService {

    private final ChatClient chatClient;

    public AIServiceImpl(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Override
    public String test(String message) {
        return generateContent(message);
    }

    @Override
    public String generateBusinessDescription(BusinessDescriptionRequest request) {

        String prompt = String.format(
                PromptTemplates.BUSINESS_DESCRIPTION,
                request.businessName(),
                request.category(),
                request.services()
        );

        return generateContent(prompt);
    }

    @Override
    public String generatePromotionalPost(PromotionalPostRequest request) {

        String prompt = String.format(
                PromptTemplates.PROMOTIONAL_POST,
                request.keywords()
        );

        return generateContent(prompt);
    }

    @Override
    public String generateCommunityAnnouncement(CommunityAnnouncementRequest request) {

        String prompt = String.format(
                PromptTemplates.COMMUNITY_ANNOUNCEMENT,
                request.eventName(),
                request.date(),
                request.location()
        );

        return generateContent(prompt);
    }


    private String generateContent(String prompt) {
        try {
            return chatClient
                    .prompt()
                    .user(prompt)
                    .call()
                    .content();
        } catch (Exception e) {
            throw new AIServiceException(
                    "Unable to generate AI content. Please try again later.",
                    e
            );
        }
    }
}