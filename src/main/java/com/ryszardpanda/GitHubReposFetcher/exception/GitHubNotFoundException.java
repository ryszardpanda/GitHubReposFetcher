package com.ryszardpanda.GitHubReposFetcher.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GitHubNotFoundException extends RuntimeException{
  private final HttpStatus httpStatus;
    public GitHubNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
