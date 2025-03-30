FROM openjdk:21-jdk

LABEL maintainer="Rychu"

COPY target/GitHubReposFetcher-0.0.1-SNAPSHOT.jar GitHubReposFetcher-0.0.1-SNAPSHOT.jar

EXPOSE 7777

ENTRYPOINT ["java", "-jar", "GitHubReposFetcher-0.0.1-SNAPSHOT.jar"]