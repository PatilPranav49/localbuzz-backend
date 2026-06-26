package com.example.localbuzz.business.exception;

public class BusinessAccessDeniedException extends RuntimeException {

    public BusinessAccessDeniedException(String message) {
        super(message);
    }
}
