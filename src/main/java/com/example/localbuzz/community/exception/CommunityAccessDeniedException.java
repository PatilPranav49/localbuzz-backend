package com.example.localbuzz.community.exception;

public class CommunityAccessDeniedException extends RuntimeException {

    public CommunityAccessDeniedException(String message) {
        super(message);
    }
}
