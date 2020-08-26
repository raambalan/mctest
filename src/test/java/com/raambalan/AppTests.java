package com.raambalan;

import com.raambalan.mastercard.CityConnectIntegerationTest;
import com.raambalan.mastercard.service.CityConnectServiceImplTest;
import com.raambalan.mastercard.controller.CityConnectControllerTest;
import com.raambalan.mastercard.controller.CityConnectWebLayerTest;
import com.raambalan.mastercard.model.GraphTest;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
        CityConnectControllerTest.class,
        CityConnectWebLayerTest.class,
        CityConnectServiceImplTest.class,
        GraphTest.class,
        CityConnectIntegerationTest.class}

)
public class AppTests {
//    @Test
//    void applicationContextLoads() {
//    }
}
