package com.ryszardpanda.GitHubReposFetcher.controller;

import com.ryszardpanda.GitHubReposFetcher.mapper.GitHubRepoInfoMapper;
import com.ryszardpanda.GitHubReposFetcher.model.GitHubRepoInFoUpdateDTO;
import com.ryszardpanda.GitHubReposFetcher.model.GitHubRepositoryDetailsDTO;
import com.ryszardpanda.GitHubReposFetcher.model.GitHubRepositoryEntity;
import com.ryszardpanda.GitHubReposFetcher.service.GitHubRepoInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GitHubRepoInfoController {
    private final GitHubRepoInfoService gitHubRepoInfoService;
    private final GitHubRepoInfoMapper gitHubRepoInfoMapper;

    @GetMapping("/repositories/{owner}/{repoName}")
    public GitHubRepositoryDetailsDTO getRepoInfo(@PathVariable String owner, @PathVariable String repoName) {
        log.info("New request for endpoint GET/repositories/" + owner + "/" + repoName + " logged");
        return gitHubRepoInfoMapper.toDetailsDTOFromEntity(gitHubRepoInfoService.getRepo(owner, repoName));
    }

    @PostMapping("/repositories/{owner}/{repoName}")
    public GitHubRepositoryDetailsDTO saveRepo(@PathVariable String owner, @PathVariable String repoName) {
        log.info("New request for endpoint POST/repositories/" + owner + "/" + repoName + " logged");
        return gitHubRepoInfoMapper.toDetailsDTOFromEntity(gitHubRepoInfoService.saveRepo(owner, repoName));
    }

    @GetMapping("/local/repositories/{owner}/{repoName}")
    public GitHubRepositoryDetailsDTO getLocalRepos(@PathVariable String owner, @PathVariable String repoName) {
        log.info("New request for endpoint GET/local/repositories/" + owner + "/" + repoName + " logged");
        return gitHubRepoInfoMapper.toDetailsDTOFromEntity(gitHubRepoInfoService
                .getRepoFromDb(GitHubRepositoryEntity.createFullName(owner, repoName)));
    }

    @PutMapping("/repositories/{owner}/{repoName}")
    public GitHubRepositoryDetailsDTO updateGitHubRepoLocal(@PathVariable String owner,
                                                            @PathVariable String repoName,
                                                            @RequestBody GitHubRepoInFoUpdateDTO gitHubRepoInFoUpdateDTO) {
        log.info("New request for endpoint PUT/repositories/" + owner + "/" + repoName + " logged");
        return gitHubRepoInfoMapper.toDetailsDTOFromEntity(gitHubRepoInfoService.updateGitHubRepo(owner, repoName, gitHubRepoInFoUpdateDTO));
    }

    @DeleteMapping("/repositories/{owner}/{repoName}")
    public void deleteRepo(@PathVariable String owner, @PathVariable String repoName){
        log.info("New request for endpoint DELETE/repositories/" + owner + "/" + repoName + " logged");
        gitHubRepoInfoService.deleteRepo(owner, repoName);
    }
}
