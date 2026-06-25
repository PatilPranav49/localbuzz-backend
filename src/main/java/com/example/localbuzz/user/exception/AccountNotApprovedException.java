package com.example.localbuzz.user.exception;

public class AccountNotApprovedException
        extends RuntimeException {

    public AccountNotApprovedException(String message) {
        super(message);
    }
}