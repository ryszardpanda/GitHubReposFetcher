package com.ryszardpanda.GitHubReposFetcher.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GitHubRepositoryDTO {
    @JsonProperty("full_name")
    private String fullName;
    private String description;
    @JsonProperty("clone_url")
    private String cloneUrl;
    @JsonProperty("stargazers_count")
    private int stars;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}
