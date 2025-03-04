package com.ryszardpanda.GitHubReposFetcher.client;

import com.ryszardpanda.GitHubReposFetcher.exception.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {

    @Bean
    public ErrorDecoder gitHubFetcherErrorDecoder(){
        return new CustomErrorDecoder();
    }
}

