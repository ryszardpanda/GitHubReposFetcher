package com.ryszardpanda.GitHubReposFetcher.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GitHubRepositoryDetailsDTO {
    private Long id;
    private String fullName;
    private String description;
    private String cloneUrl;
    private int stars;
    private LocalDateTime createdAt;
}
