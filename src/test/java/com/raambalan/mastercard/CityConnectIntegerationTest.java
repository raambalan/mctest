package com.raambalan.mastercard;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
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


    @Test
    void directPath() throws Exception {
        this.mockMvc.perform(
                get("/connected")
                        .param("origin", "New York")
                        .param("destination", "Boston"))
                .andReturn().getResponse().getContentAsString().equalsIgnoreCase("Yes");
        ;

    }

    @Test
    void multiCityPath() throws Exception {
        this.mockMvc.perform(
                get("/connected")
                        .param("origin", "Philadelphia York")
                        .param("destination", "Boston"))
                .andReturn().getResponse().getContentAsString().equalsIgnoreCase("Yes");
        ;

    }

    @Test
    void pathNotFound() throws Exception {
        this.mockMvc.perform(
                get("/connected")
                        .param("origin", "Trenton")
                        .param("destination", "Boston"))
                .andReturn().getResponse().getContentAsString().equalsIgnoreCase("No");
        ;
    }
}
