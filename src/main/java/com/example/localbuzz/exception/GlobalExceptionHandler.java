package com.example.localbuzz.exception;

import com.example.localbuzz.ai.exception.AIServiceException;
import com.example.localbuzz.business.exception.BusinessAccessDeniedException;
import com.example.localbuzz.business.exception.BusinessNotFoundException;
import com.example.localbuzz.common.file.FileStorageException;
import com.example.localbuzz.community.exception.CommunityAccessDeniedException;
import com.example.localbuzz.community.exception.CommunityPostNotFoundException;
import com.example.localbuzz.user.exception.AccountNotApprovedException;
import com.example.localbuzz.user.exception.EmailAlreadyExistsException;
import com.example.localbuzz.user.exception.InvalidCredentialsException;
import com.example.localbuzz.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExists(
            EmailAlreadyExistsException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(
            UserNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentials(
            InvalidCredentialsException ex) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ex.getMessage());
    }

    @ExceptionHandler(AccountNotApprovedException.class)
    public ResponseEntity<String> handleAccountNotApproved(
            AccountNotApprovedException ex) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ex.getMessage());
    }

    @ExceptionHandler(BusinessNotFoundException.class)
    public ResponseEntity<String> handleBusinessNotFound(
            BusinessNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(CommunityPostNotFoundException.class)
    public ResponseEntity<String> handleCommunityPostNotFound(
            CommunityPostNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler({
            BusinessAccessDeniedException.class,
            CommunityAccessDeniedException.class
    })
    public ResponseEntity<String> handleAccessDenied(
            RuntimeException ex) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ex.getMessage());
    }


    @ExceptionHandler(AIServiceException.class)
    public ResponseEntity<String> handleAIServiceException(
            AIServiceException ex) {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ex.getMessage());
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<String> handleFileStorageException(
            FileStorageException ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong. Please try again later.");
    }
}
