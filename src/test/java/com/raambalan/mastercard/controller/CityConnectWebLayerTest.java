package com.raambalan.mastercard.controller;

import com.raambalan.mastercard.CityConnectQuery;
import com.raambalan.mastercard.CityConnectRepository;
import com.raambalan.mastercard.CityConnectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest
public class CityConnectWebLayerTest {
    @Autowired
    private CityConnectController cityConnectController;

    @Test
    void onlyCustomerControllerIsLoaded() {
        assertThat(cityConnectController).isNotNull();
    }

    @TestConfiguration
    static class MyTestConfiguration {
        @Bean
        CityConnectQuery cityConnectQuery() {
            return new CityConnectService(new CityConnectRepository());
        }

    }

}
