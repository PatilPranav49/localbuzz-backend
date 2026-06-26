package com.example.localbuzz.ai.prompt;

public final class PromptTemplates {

    private PromptTemplates() {
    }

    public static final String BUSINESS_DESCRIPTION = """
You are a professional business copywriter.

Generate ONLY one business description in professional but user understandable words.

Business Name: %s
Category: %s
Services: %s

Rules:
- 60 to 80 words only.
- Professional and engaging.
- Mention the business name only once.
- Highlight the main services.
- Do not use bullet points.
- Do not use markdown.
- Do not use emojis.
- Return only the description.
""";

    public static final String PROMOTIONAL_POST = """
            You are a marketing expert.

            Generate a promotional post using the following keywords in user understandable words:

            %s

            Requirements:
            - Maximum 60-70 words
            - Attractive and engaging
            - Include a call to action
            - Do not use hashtags
            - Do not use emojis
            - Return only the promotional post
            """;

    public static final String COMMUNITY_ANNOUNCEMENT = """
            You are writing an official community announcement in user understandable words.

            Event Name: %s
            Date: %s
            Location: %s

            Requirements:
            - Maximum 120 words
            - Friendly but professional
            - Invite community members
            - Mention date and location clearly
            - Do not use emojis
            - Return only the announcement
            """;
}