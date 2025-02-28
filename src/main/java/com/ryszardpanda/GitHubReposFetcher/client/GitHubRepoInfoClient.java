package com.ryszardpanda.GitHubReposFetcher.client;

import com.ryszardpanda.GitHubReposFetcher.model.GitHubRepoInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "apigithub", url = "https://api.github.com/")
public interface GitHubRepoInfoClient {
    @RequestMapping(method = RequestMethod.GET, value = "/repos/{owner}/{repo}")
    GitHubRepoInfoDTO getRepoInfo(@PathVariable("owner") String owner, @PathVariable("repo") String repositoryName);
}
