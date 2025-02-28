package com.ryszardpanda.GitHubReposFetcher.mapper;

import com.ryszardpanda.GitHubReposFetcher.model.GitHubRepoInfo;
import com.ryszardpanda.GitHubReposFetcher.model.GitHubRepoInfoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GitHubRepoInfoMapper{
    GitHubRepoInfo gitHubRepoInfoDTOtogitHubRepoInfo(GitHubRepoInfoDTO gitHubRepoInfoDTO);
    GitHubRepoInfoDTO gitHubRepoInfoToGitHubRepoInfoDTO(GitHubRepoInfo gitHubRepoInfo);
}
