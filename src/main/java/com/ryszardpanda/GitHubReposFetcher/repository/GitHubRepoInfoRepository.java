package com.ryszardpanda.GitHubReposFetcher.repository;

import com.ryszardpanda.GitHubReposFetcher.model.GitHubRepositoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GitHubRepoInfoRepository extends JpaRepository<GitHubRepositoryEntity, Long> {
    Optional<GitHubRepositoryEntity> findByFullName(String fullName);
}
