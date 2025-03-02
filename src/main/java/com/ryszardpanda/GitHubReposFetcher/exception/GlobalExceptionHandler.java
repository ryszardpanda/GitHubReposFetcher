package com.ryszardpanda.GitHubReposFetcher.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({LocalRepositoryNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleLocalRepositoryNotFoundException(
            LocalRepositoryNotFoundException ex) {
        ErrorMessage errorMessage = new ErrorMessage(
                ex.getHttpStatus().value(),
                ex.getHttpStatus().getReasonPhrase(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorMessage, ex.getHttpStatus());
    }
}
