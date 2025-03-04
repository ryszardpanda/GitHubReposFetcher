package com.ryszardpanda.GitHubReposFetcher.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LocalRepositoryNotFoundException extends RuntimeException{
    private final HttpStatus httpStatus;
    public LocalRepositoryNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
