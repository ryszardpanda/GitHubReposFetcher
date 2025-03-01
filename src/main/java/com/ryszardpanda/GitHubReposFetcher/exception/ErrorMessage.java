package com.ryszardpanda.GitHubReposFetcher.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorMessage {
    private String message;
}