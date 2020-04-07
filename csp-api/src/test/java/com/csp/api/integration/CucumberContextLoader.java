package com.csp.api.integration;

import com.csp.api.TestUtils;
import com.csp.api.domain.repository.RecordRepository;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberContextLoader {

    @Autowired
    private RecordRepository recordRepository;

    @LocalServerPort
    int port;
    private HttpClient httpClient;
    private String baseUrl;
    private String recordJson;

    @Before
    public void setupTestContext() {
        httpClient = HttpClient.newHttpClient();
        baseUrl = "http://localhost:" + port;
        recordJson = TestUtils.getJsonFromFile("record.json");
    }

    @After
    public void cleanUpTestContext() {
        recordRepository.deleteAll();
    }

    @Given("^Record doesn't exist$")
    public void record_doesnt_exist() {

    }

    @When("^I call scp to process a record$")
    public void i_call_csp_service() throws IOException, InterruptedException {
        post("/api/records", recordJson);
    }

    @Then("^A new record created")
    public void a_new_record_created() throws IOException, InterruptedException {
        HttpResponse<String> response = get("/api/records");
        assertEquals(200, response.statusCode());
        assertEquals(recordJson, response.body());
    }

    private HttpResponse<String> get(String urlPath) throws IOException, InterruptedException {
        return httpClient
                .send(HttpRequest.newBuilder()
                                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                                .uri(URI.create(baseUrl + urlPath))
                                .GET()
                                .build(),
                        HttpResponse.BodyHandlers.ofString()
                );
    }

    private HttpResponse<String> post(String urlPath, String body) throws IOException, InterruptedException {
        return httpClient
                .send(HttpRequest.newBuilder()
                                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                                .uri(URI.create(baseUrl + urlPath))
                                .POST(HttpRequest.BodyPublishers.ofString(body))
                                .build(),
                        HttpResponse.BodyHandlers.ofString()
                );
    }
}
