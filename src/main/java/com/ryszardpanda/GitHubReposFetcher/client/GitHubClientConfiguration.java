package com.ryszardpanda.GitHubReposFetcher.client;

import com.ryszardpanda.GitHubReposFetcher.exception.GitHubCustomErrorDecoder;
import feign.Logger;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.ryszardpanda.wiremockfortesting.proxy")
public class GitHubClientConfiguration {

    @Bean
    public ErrorDecoder gitHubFetcherErrorDecoder(){
        return new GitHubCustomErrorDecoder();
    }

    @Bean
    public Retryer feignRetryer(){
        return new Retryer.Default(100, 500, 10);
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}

