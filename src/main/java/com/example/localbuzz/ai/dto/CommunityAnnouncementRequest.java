package com.example.localbuzz.ai.dto;

import jakarta.validation.constraints.NotBlank;

public record CommunityAnnouncementRequest(

        @NotBlank
        String eventName,

        @NotBlank
        String date,

        @NotBlank
        String location

) {
}