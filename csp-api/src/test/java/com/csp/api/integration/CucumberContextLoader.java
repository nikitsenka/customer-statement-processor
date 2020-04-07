package com.csp.api.integration;

import com.csp.api.TestUtils;
import com.csp.api.domain.repository.ReportRepository;
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
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberContextLoader {

    @Autowired
    private ReportRepository recordRepository;

    @LocalServerPort
    int port;
    private HttpClient httpClient;
    private String baseUrl;

    @Before
    public void setupTestContext() {
        httpClient = HttpClient.newHttpClient();
        baseUrl = "http://localhost:" + port;
    }

    @After
    public void cleanUpTestContext() {
       // recordRepository.deleteAll();
    }

    @Given("^Service operates successfully$")
    public void service_operates_successfully() {

    }

    @When("^Service receives json of customer statement records$")
    public void json_of_records() throws IOException, InterruptedException {
        String recordJson = TestUtils.getJsonFromFile("records.json");
        HttpResponse<String> result = post("/api/records/process", recordJson, APPLICATION_JSON_VALUE);
        assertEquals(200, result.statusCode());
    }

    @When("^Service receives xml of customer statement records$")
    public void xml_of_records() throws IOException, InterruptedException {
        String recordsXml = TestUtils.getJsonFromFile("records.xml");
        HttpResponse<String> result = post("/api/records/process", recordsXml, APPLICATION_XML_VALUE);
        assertEquals(200, result.statusCode());
    }

    @Then("^A new report created")
    public void report_created() throws IOException, InterruptedException {
        HttpResponse<String> reportsResponse = get("/api/reports");
        assertEquals(200, reportsResponse.statusCode());
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

    private HttpResponse<String> post(String urlPath, String body, String contentType) throws IOException, InterruptedException {
        return httpClient
                .send(HttpRequest.newBuilder()
                                .header(HttpHeaders.CONTENT_TYPE, contentType)
                                .uri(URI.create(baseUrl + urlPath))
                                .POST(HttpRequest.BodyPublishers.ofString(body))
                                .build(),
                        HttpResponse.BodyHandlers.ofString()
                );
    }
}
