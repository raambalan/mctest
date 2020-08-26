package com.raambalan.mastercard.repository;

import com.raambalan.mastercard.model.CityPathGraph;
import com.raambalan.mastercard.model.Graph;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CityConnectRepositoryTest {
    @Autowired
    CityConnectRepository repository;

    @Test
    void loadCityPaths() throws IOException {
        Graph g = repository.loadPaths("city.txt");
        assertEquals(g.getCity("Boston").getName(), "Boston");
        assertTrue(g.getCity("Boston").getAdjCities().contains("Newark"));
        assertTrue(g.getCity("Boston").getAdjCities().contains("New York"));
        assertFalse(g.getCity("Boston").getAdjCities().contains("Trenton2343"));
        assertEquals(g.getCity("Trenton12").getName(), "CityNotfound");
    }
}
