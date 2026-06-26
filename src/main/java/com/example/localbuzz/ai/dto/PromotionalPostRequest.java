package com.example.localbuzz.ai.dto;

import jakarta.validation.constraints.NotBlank;

public record PromotionalPostRequest(

        @NotBlank
        String keywords

) {
}