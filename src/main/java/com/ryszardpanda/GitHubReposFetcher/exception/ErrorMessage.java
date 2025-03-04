package com.ryszardpanda.GitHubReposFetcher.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Data
public class ErrorMessage {
    private int status;
    private String error;
    private String message;
    private String timestamp;

    public ErrorMessage(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }
}