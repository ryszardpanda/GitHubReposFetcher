package com.ryszardpanda.GitHubReposFetcher.service;

import com.ryszardpanda.GitHubReposFetcher.client.GitHubRepoInfoClient;
import com.ryszardpanda.GitHubReposFetcher.mapper.GitHubRepoInfoMapper;
import com.ryszardpanda.GitHubReposFetcher.model.GitHubRepoInfo;
import com.ryszardpanda.GitHubReposFetcher.model.GitHubRepoInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GitHubRepoInfoService {
    private final GitHubRepoInfoClient gitHubRepoInfoClient;
    private final GitHubRepoInfoMapper gitHubRepoInfoMapper;

    public GitHubRepoInfo getRepo(String owner, String repositoryName) {
        GitHubRepoInfoDTO repoInfoDTO = gitHubRepoInfoClient.getRepoInfo(owner, repositoryName);
        return gitHubRepoInfoMapper.gitHubRepoInfoDTOtogitHubRepoInfo(repoInfoDTO);
    }
}
