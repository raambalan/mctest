package com.raambalan.mastercard.controller;

import com.raambalan.mastercard.CityConnectQuery;
import com.raambalan.mastercard.model.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class CityConnectControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ApplicationContext applicationContext;

    @MockBean
    CityConnectQuery connectQuery;

    @BeforeEach
    void printApplicationContext() {
        Arrays.stream(applicationContext.getBeanDefinitionNames())
                .map(name -> applicationContext.getBean(name).getClass().getName())
                .sorted()
                .forEach(System.out::println);
        when(connectQuery.findPath("City1", "City2")).thenReturn(Graph.ResultType.PATH_FOUND);
        when(connectQuery.findPath("City2", "City3")).thenReturn(Graph.ResultType.NO_PATH_FOUND);
        when(connectQuery.findPath("City5", "City6")).thenReturn(Graph.ResultType.SOURCE_NOT_FOUND);

    }

    @Test
    void findPathTest() throws Exception {

        mockMvc.perform(
                get("/connected")
                        .param("origin", "City1")
                        .param("destination", "City2"))
                .andReturn().getResponse().getContentAsString().equalsIgnoreCase("Yes");

        mockMvc.perform(
                get("/connected")
                        .param("origin", "City2")
                        .param("destination", "City3"))
                .andReturn().getResponse().getContentAsString().equalsIgnoreCase("No");


        mockMvc.perform(
                get("/connected")
                        .param("origin", "City5")
                        .param("destination", "City6"))
                .andReturn().getResponse().getContentAsString().equalsIgnoreCase("No");

    }
}
