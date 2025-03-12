package com.ryszardpanda.GitHubReposFetcher.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GitHubRepositoryAspect {
    @Before("execution(* com.ryszardpanda.GitHubReposFetcher.service.*.*(..))")
    public void logBeforeMethodExecution() {
        System.out.println("przed wykonaniem metody z serwisu");
    }

    @Pointcut("execution(* com.ryszardpanda.GitHubReposFetcher.service.*.*(..))")
    public void serviceMethods() {
    }

    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void afterServiceMethodExecution(Object result) {
        System.out.println("Metoda zwróciła: " + result);
    }

    @Around("execution(* com.ryszardpanda.GitHubReposFetcher.service.*.*(..))")
    public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("Wykoanie " + joinPoint.getSignature().getName() + " : " + (endTime - startTime) + " ms");
        return result;
    }

    @Pointcut("execution(* com.ryszardpanda.GitHubReposFetcher.controller.*.get*(..)) || execution(* com.ryszardpanda.GitHubReposFetcher.controller.*.find*(..))")
    public void controllerGetOrFindMethods() {
    }

    @Before("controllerGetOrFindMethods()")
    public void beforeControllerGetOrFindMethods() {
        System.out.println("jestem metodą GET/FIND");
    }

    @Pointcut("execution(* com.ryszardpanda.GitHubReposFetcher.client.GitHubRepoInfoClient.*(..))")
    public void clientMethods() {}

    @Around("clientMethods()")
    public Object aroundClientMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long finishTime = System.currentTimeMillis() - startTime;
        System.out.println("Czas requestu " + joinPoint.getSignature().getName() + " do GitHubRepositoryClient wynosił:" +
                finishTime + " ms");
        return proceed;
    }
}
