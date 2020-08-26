package com.raambalan.mastercard.model;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class GraphTest {
    @Autowired
    Graph graph;
    @TestConfiguration
    static  class MytestConfig{
        @Bean
        Graph graph(){
            return new CityPathGraph();
        }
    }

    @Test
    void testBuildGraph(){
        graph.addPath("City1","City2");
        graph.addPath("City2","City3");
        graph.addPath("City4","City5");
        assertEquals(graph.getCity("City1").getName(),"City1");
        assertEquals(graph.getCity("City2").getName(),"City2");
        assertEquals(graph.getCity("City3").getName(),"City3");
        assertEquals(graph.getCity("City4").getName(),"City4");
        assertEquals(graph.getCity("City5").getName(),"City5");


    }
}
