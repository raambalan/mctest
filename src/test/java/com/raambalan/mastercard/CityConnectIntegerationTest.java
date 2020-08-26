package com.raambalan.mastercard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CityConnectIntegerationTest {

    @Autowired
    private WebApplicationContext applicationContext;
    private MockMvc mockMvc;

    @BeforeAll
    void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();
    }

    @LocalServerPort
    private int port;
    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testDirectPathWithRandomPort() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(createURLWithPort("/connected"))
                .queryParam("origin", URLEncoder.encode("New York", "UTF-8"))
                .queryParam("destination", URLEncoder.encode("Boston", "UTF-8"));
        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        String actual = response.getBody().trim();
        Assertions.assertTrue(actual.contains("Yes"));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    void directPathWitMockMvc() throws Exception {
        Assertions.assertTrue(this.mockMvc.perform(
                get("/connected")
                        .param("origin", "New York")
                        .param("destination", "Boston"))
                .andReturn().getResponse().getContentAsString().equalsIgnoreCase("Yes"));
        ;

    }

    @Test
    void multiCityPathWitMockMvc() throws Exception {
        Assertions.assertTrue(this.mockMvc.perform(
                get("/connected")
                        .param("origin", "Philadelphia")
                        .param("destination", "Boston"))
                .andReturn().getResponse().getContentAsString().equalsIgnoreCase("Yes"));
        ;

    }

    @Test
    void pathNotFoundWitMockMvc() throws Exception {
        Assertions.assertTrue(this.mockMvc.perform(
                get("/connected")
                        .param("origin", "Trenton")
                        .param("destination", "Boston"))
                .andReturn().getResponse().getContentAsString().equalsIgnoreCase("No"));
        ;
    }
}
