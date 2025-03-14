package com.ryszardpanda.GitHubReposFetcher.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({LocalRepositoryNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleLocalRepositoryNotFoundException(
            LocalRepositoryNotFoundException ex) {
        log.error("LocalRepositoryNotFoundException was thrown, " + ex.getMessage());
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage()
        );
    }

    @ExceptionHandler({GitHubNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleGitHubNotFoundException(
            GitHubNotFoundException ex) {
        log.error("GitHubNotFoundException was thrown, " + ex.getMessage());
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage()
        );
    }
}
