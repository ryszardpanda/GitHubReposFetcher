package com.ryszardpanda.GitHubReposFetcher.controller;

import com.ryszardpanda.GitHubReposFetcher.mapper.GitHubRepoInfoMapper;
import com.ryszardpanda.GitHubReposFetcher.model.GitHubRepositoryDetailsDTO;
import com.ryszardpanda.GitHubReposFetcher.service.GitHubRepoInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GitHubRepoInfoController {
    private final GitHubRepoInfoService gitHubRepoInfoService;
    private final GitHubRepoInfoMapper gitHubRepoInfoMapper;

    @GetMapping("/repositories/{owner}/{repoName}")
    public GitHubRepositoryDetailsDTO getRepoInfo(@PathVariable String owner, @PathVariable String repoName){
       return gitHubRepoInfoMapper.gitHubRepositoryEntityToGitHubRepositoryDetailsDTO(gitHubRepoInfoService.getRepo(owner, repoName));
    }

    @PostMapping("/repositories/{owner}/{repoName}")
    public GitHubRepositoryDetailsDTO saveRepo(@PathVariable String owner, @PathVariable String repoName){
       return gitHubRepoInfoMapper.gitHubRepositoryEntityToGitHubRepositoryDetailsDTO(gitHubRepoInfoService.saveRepo(owner, repoName));
    }

    @GetMapping("/local/repositories/{owner}/{repoName}")
    public GitHubRepositoryDetailsDTO getLocalRepos(@PathVariable String owner, @PathVariable String repoName){
        String fullName = owner + "/" + repoName;
        return gitHubRepoInfoMapper.gitHubRepositoryEntityToGitHubRepositoryDetailsDTO(gitHubRepoInfoService.getRepoFromDb(fullName));
    }
}
