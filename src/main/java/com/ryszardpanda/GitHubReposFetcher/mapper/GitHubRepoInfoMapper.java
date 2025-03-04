package com.ryszardpanda.GitHubReposFetcher.mapper;

import com.ryszardpanda.GitHubReposFetcher.model.GitHubRepositoryDetailsDTO;
import com.ryszardpanda.GitHubReposFetcher.model.GitHubRepositoryEntity;
import com.ryszardpanda.GitHubReposFetcher.model.GitHubRepositoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GitHubRepoInfoMapper {
    GitHubRepositoryEntity toEnityFromDTO(GitHubRepositoryDTO gitHubRepositoryDTO);

    GitHubRepositoryDetailsDTO toDetailsDTOFromEntity(GitHubRepositoryEntity gitHubRepositoryEntity);
}

