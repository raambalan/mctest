package com.raambalan.mastercard;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.Arrays;

import static com.raambalan.mastercard.model.Graph.ResultType;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CityConnectServiceTest {
    @Autowired
    private ApplicationContext applicationContext;

    @BeforeAll
    void setUp() throws IOException {
        Arrays.stream(applicationContext.getBeanDefinitionNames())
                .map(name -> applicationContext.getBean(name).getClass().getName())
                .sorted()
                .forEach(System.out::println);
    }

    @TestConfiguration
    static class MyTestConfiguration {
        @Bean
        public CityConnectQuery connectQuery() {
            return new CityConnectService(new CityConnectRepository());
        }

    }


    @Autowired
    CityConnectQuery connectQuery;

    @Test
    void directPath() {
        assertEquals(connectQuery.findPath("New York", "Boston"), ResultType.PATH_FOUND);

    }

    @Test
    void multiCityPath() {
        assertEquals(connectQuery.findPath("Philadelphia", "Boston"), ResultType.PATH_FOUND);
    }

    @Test
    void pathNotFound() {
        assertEquals(connectQuery.findPath("Trenton", "Boston"), ResultType.NO_PATH_FOUND);
    }

    @Test
    void cityNotFound() {
        assertEquals(connectQuery.findPath("Trenton12", "Boston"), ResultType.SOURCE_NOT_FOUND);
        assertEquals(connectQuery.findPath("Boston", "Trenton12"), ResultType.DEST_NOT_FOUND);
    }

}
