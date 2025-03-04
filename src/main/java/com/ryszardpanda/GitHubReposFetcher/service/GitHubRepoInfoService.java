package com.ryszardpanda.GitHubReposFetcher.service;

import com.ryszardpanda.GitHubReposFetcher.exception.LocalRepositoryNotFoundException;
import com.ryszardpanda.GitHubReposFetcher.model.GitHubRepoInFoUpdateDTO;
import com.ryszardpanda.GitHubReposFetcher.repository.GitHubRepoInfoRepository;
import com.ryszardpanda.GitHubReposFetcher.client.GitHubRepoInfoClient;
import com.ryszardpanda.GitHubReposFetcher.mapper.GitHubRepoInfoMapper;
import com.ryszardpanda.GitHubReposFetcher.model.GitHubRepositoryEntity;
import com.ryszardpanda.GitHubReposFetcher.model.GitHubRepositoryDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GitHubRepoInfoService {
    private final GitHubRepoInfoClient gitHubRepoInfoClient;
    private final GitHubRepoInfoMapper gitHubRepoInfoMapper;
    private final GitHubRepoInfoRepository gitHubRepoInfoRepository;

    public GitHubRepositoryEntity getRepo(String owner, String repositoryName) {
        GitHubRepositoryDTO repoInfoDTO = gitHubRepoInfoClient.getRepoInfo(owner, repositoryName);
        return gitHubRepoInfoMapper.toEnityFromDTO(repoInfoDTO);
    }

    public GitHubRepositoryEntity saveRepo(String owner, String repositoryName) {
        GitHubRepositoryEntity repo = getRepo(owner, repositoryName);
        return gitHubRepoInfoRepository.save(repo);
    }

    public GitHubRepositoryEntity getRepoFromDb(String fullName) {
        return gitHubRepoInfoRepository.findByFullName(fullName).orElseThrow(() ->
                new LocalRepositoryNotFoundException("Repository with name: " + fullName + " are not found, check data and try again", HttpStatus.NOT_FOUND));
    }

    @Transactional
    public GitHubRepositoryEntity updateGitHubRepo(String owner, String repoName, GitHubRepoInFoUpdateDTO gitHubRepoInfoUpdateDTO) {
        String fullName = GitHubRepositoryEntity.createFullName(owner, repoName);
        GitHubRepositoryEntity gitHubRepositoryEntity = gitHubRepoInfoRepository.findByFullName(fullName).orElseThrow(() ->
                new LocalRepositoryNotFoundException("Repository with name: " + fullName + " are not found, check data and try again", HttpStatus.NOT_FOUND));

        gitHubRepositoryEntity.update(gitHubRepoInfoUpdateDTO);
        return gitHubRepoInfoRepository.save(gitHubRepositoryEntity);
    }

    @Transactional
    public void deleteRepo(String owner, String repoName) {
        String fullName = GitHubRepositoryEntity.createFullName(owner, repoName);
        GitHubRepositoryEntity gitHubRepositoryEntity = gitHubRepoInfoRepository.findByFullName(fullName).orElseThrow(() ->
                new LocalRepositoryNotFoundException("Repository with name: " + fullName + " are not found, check data and try again", HttpStatus.NOT_FOUND));
        gitHubRepoInfoRepository.delete(gitHubRepositoryEntity);
    }
}
