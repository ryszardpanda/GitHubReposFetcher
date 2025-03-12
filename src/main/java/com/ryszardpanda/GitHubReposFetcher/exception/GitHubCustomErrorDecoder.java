package com.ryszardpanda.GitHubReposFetcher.exception;

import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GitHubCustomErrorDecoder implements ErrorDecoder {
    private ErrorDecoder errorDecoder = new ErrorDecoder.Default();
    private static final Logger logger = LoggerFactory.getLogger(GitHubCustomErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {
        FeignException exception = feign.FeignException.errorStatus(methodKey, response);
        RetryableException retryableException = new RetryableException(response.status(),
                exception.getMessage(),
                response.request().httpMethod(),
                exception,
                500L,
                response.request());
        switch (response.status()) {
            case 404:
                return new GitHubNotFoundException("GitHub repository with provided name NOT FOUND, please ensure correctness of data and try again", HttpStatus.NOT_FOUND);
            case 500:
            case 502:
            case 503:
            case 504:
                logger.info("Throwing RetryableException and Retrying the request", response.status());
                return retryableException;
            default:
                return errorDecoder.decode(methodKey, response);
        }
    }
}
