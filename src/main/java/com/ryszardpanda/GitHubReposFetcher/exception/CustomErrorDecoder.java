package com.ryszardpanda.GitHubReposFetcher.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;


public class CustomErrorDecoder implements ErrorDecoder {
    private ErrorDecoder errorDecoder = new ErrorDecoder.Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 404:
                return new GitHubNotFoundException("GitHub repository with provided name NOT FOUND, please ensure corectnes of data and try again", HttpStatus.NOT_FOUND);
            default:
                return errorDecoder.decode(methodKey, response);
        }
    }
}
