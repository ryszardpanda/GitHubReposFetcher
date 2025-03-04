package com.ryszardpanda.GitHubReposFetcher.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GitHubRepositoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String description;
    private String cloneUrl;
    private int stars;
    private LocalDateTime createdAt;

    public static String createFullName(String owner, String repoName){
        String fullName = owner + "/" + repoName;
        return fullName;
    }

    public void update(GitHubRepoInFoUpdateDTO updateDTO) {
        fullName = updateDTO.getFullName();
        description = updateDTO.getDescription();
        cloneUrl = updateDTO.getCloneUrl();
        stars = updateDTO.getStars();
        createdAt = updateDTO.getCreatedAt();
    }
}
