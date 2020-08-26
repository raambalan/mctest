package com.raambalan.mastercard.service;

import com.raambalan.mastercard.model.CityPathGraph;
import com.raambalan.mastercard.model.Graph;
import com.raambalan.mastercard.repository.CityConnectRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.Arrays;

import static com.raambalan.mastercard.model.Graph.ResultType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CityConnectServiceImplTest {
    @Autowired
    private ApplicationContext applicationContext;

    @BeforeAll
    void setUp() {
        Arrays.stream(applicationContext.getBeanDefinitionNames())
                .map(name -> applicationContext.getBean(name).getClass().getName())
                .sorted()
                .forEach(System.out::println);
    }


    @Autowired
    CityConnectService connectService;

    @Test
    void directPath() {
        assertEquals(connectService.findPath("New York", "Boston"), ResultType.PATH_FOUND);
    }

    @Test
    void multiCityPath() {
        assertEquals(connectService.findPath("Philadelphia", "Boston"), ResultType.PATH_FOUND);
    }

    @Test
    void pathNotFound() {
        assertEquals(connectService.findPath("Trenton", "Boston"), ResultType.NO_PATH_FOUND);
    }

    @Test
    void cityNotFound() {
        assertEquals(connectService.findPath("Trenton12", "Boston"), ResultType.SOURCE_NOT_FOUND);
        assertEquals(connectService.findPath("Boston", "Trenton12"), ResultType.DEST_NOT_FOUND);
    }


    @TestConfiguration
    static class MyTestConfiguration {
        @MockBean
        CityConnectRepository repository;

        @Bean
        public CityConnectService connectService() throws IOException {
            Mockito.when(repository.loadPaths(any())).thenReturn(buildGraph());
            return new CityConnectServiceImpl(repository);
        }

        Graph buildGraph() {
            Graph g = new CityPathGraph();
            g.addPath("New York", "Boston");
            g.addPath("Philadelphia", "Boston");
            g.addPath("Trenton", "Albany");
            return g;
        }

    }

}
