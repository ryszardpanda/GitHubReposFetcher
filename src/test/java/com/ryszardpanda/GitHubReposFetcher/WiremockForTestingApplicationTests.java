package com.ryszardpanda.GitHubReposFetcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.tomakehurst.wiremock.WireMockServer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.ryszardpanda.GitHubReposFetcher.model.GitHubRepositoryDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 7070)
public class WiremockForTestingApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WireMockServer wireMockServer;

    @BeforeEach
    void setUp() {
        wireMockServer.start();
    }

    @AfterEach
    void setDown(){
        wireMockServer.stop();
    }

    @Test
    public void getRepoInfo_RepoExist_RepoReturned() throws Exception {
        GitHubRepositoryDTO gitHubRepositoryDTO = new GitHubRepositoryDTO("rychu", "repo Rycha", "www.eloelo.pl", 5, LocalDateTime.of(2022, 11, 11, 11, 11));
        String owner = "rychuuu";
        String repoName = "rychuuu420elo";
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        String jsonResponse = objectMapper.writeValueAsString(gitHubRepositoryDTO);

        stubFor(WireMock.get(urlMatching("/repos/" + owner + "/" + repoName))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(jsonResponse)));

        //when/then

        mockMvc.perform(get("/repositories/" + owner + "/" + repoName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("repo Rycha"))
                .andExpect(jsonPath("$.fullName").value("rychu"))
                .andExpect(jsonPath("$.cloneUrl").value("www.eloelo.pl"))
                .andExpect(jsonPath("$.stars").value(5))
                .andExpect(jsonPath("$.createdAt").value("2022-11-11T11:11:00"));
    }

    @Test
    public void saveRepo_RepoExist_RepoSaved() throws Exception {
        GitHubRepositoryDTO gitHubRepositoryDTO = new GitHubRepositoryDTO("rychu", "repo Rycha", "www.eloelo.pl", 5, LocalDateTime.of(2022, 11, 11, 11, 11));
        String owner = "rychuuu";
        String repoName = "rychuuu420elo";
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        String jsonResponse = objectMapper.writeValueAsString(gitHubRepositoryDTO);

        stubFor(WireMock.get(urlMatching("/repos/" + owner + "/" + repoName))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(jsonResponse)));

        mockMvc.perform(MockMvcRequestBuilders.post("/repositories/" + owner + "/" + repoName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("repo Rycha"))
                .andExpect(jsonPath("$.fullName").value("rychu"))
                .andExpect(jsonPath("$.cloneUrl").value("www.eloelo.pl"))
                .andExpect(jsonPath("$.stars").value(5))
                .andExpect(jsonPath("$.createdAt").value("2022-11-11T11:11:00"));
    }
}
