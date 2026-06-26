package com.example.localbuzz.ai.dto;

import jakarta.validation.constraints.NotBlank;

public record BusinessDescriptionRequest(

        @NotBlank
        String businessName,

        @NotBlank
        String category,

        @NotBlank
        String services

) {
}