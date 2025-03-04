package com.ryszardpanda.GitHubReposFetcher.service;

import com.ryszardpanda.GitHubReposFetcher.client.GitHubRepoInfoClient;
import com.ryszardpanda.GitHubReposFetcher.mapper.GitHubRepoInfoMapper;
import com.ryszardpanda.GitHubReposFetcher.model.GitHubRepositoryDTO;
import com.ryszardpanda.GitHubReposFetcher.model.GitHubRepositoryEntity;
import com.ryszardpanda.GitHubReposFetcher.repository.GitHubRepoInfoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import java.time.LocalDateTime;

public class GitHubRepoInfoServiceTest {
    private  GitHubRepoInfoClient gitHubRepoInfoClient;
    private  GitHubRepoInfoMapper gitHubRepoInfoMapper;
    private  GitHubRepoInfoRepository gitHubRepoInfoRepository;
    private GitHubRepoInfoService gitHubRepoInfoService;

    @BeforeEach
    void setUp(){
        this.gitHubRepoInfoClient = Mockito.mock(GitHubRepoInfoClient.class);
        this.gitHubRepoInfoMapper = Mappers.getMapper(GitHubRepoInfoMapper.class);
        this.gitHubRepoInfoRepository = Mockito.mock(GitHubRepoInfoRepository.class);
        this.gitHubRepoInfoService = new GitHubRepoInfoService(gitHubRepoInfoClient, gitHubRepoInfoMapper, gitHubRepoInfoRepository);
    }

    @Test
    public void getRepo_RepoExist_RepoReturned(){
        //given
        String owner = "gienio";
        String repositoryName = "Repoo";
        GitHubRepositoryEntity gitHubRepositoryEntity = new GitHubRepositoryEntity(1L, "Gienioo",
                "EloElo", "www.elo.pl/", 5, (LocalDateTime.of(2022, 11, 11, 0, 0)));
        GitHubRepositoryDTO gitHubRepositoryDTO = new GitHubRepositoryDTO("Gienioo",
                "EloElo", "www.elo.pl/", 5, (LocalDateTime.of(2022, 11, 11, 0, 0)));
        Mockito.when(gitHubRepoInfoClient.getRepoInfo(owner, repositoryName)).thenReturn(gitHubRepositoryDTO);
        //when
        GitHubRepositoryEntity result = gitHubRepoInfoService.getRepo(owner, repositoryName);
        //then
        Assertions.assertEquals("Gienioo", result.getFullName());
        Assertions.assertEquals("EloElo", result.getDescription());
        Assertions.assertEquals("www.elo.pl/", result.getCloneUrl());
        Assertions.assertEquals(5, result.getStars());
        Assertions.assertEquals((LocalDateTime.of(2022, 11, 11, 0, 0)), result.getCreatedAt());
    }
}
