package com.ryszardpanda.GitHubReposFetcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GitHubReposFetcherApplication {
	public static void main(String[] args) {
		SpringApplication.run(GitHubReposFetcherApplication.class, args);
	}
}
