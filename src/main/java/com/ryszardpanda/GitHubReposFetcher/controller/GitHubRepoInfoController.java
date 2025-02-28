package com.ryszardpanda.GitHubReposFetcher.controller;

import com.ryszardpanda.GitHubReposFetcher.model.GitHubRepoInfo;
import com.ryszardpanda.GitHubReposFetcher.service.GitHubRepoInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GitHubRepoInfoController {
    private final GitHubRepoInfoService gitHubRepoInfoService;

    @GetMapping("/repositories/{owner}/{repoName}")
    public GitHubRepoInfo getRepoInfo(@PathVariable String owner, @PathVariable String repoName){
        return gitHubRepoInfoService.getRepo(owner, repoName);
    }
}
