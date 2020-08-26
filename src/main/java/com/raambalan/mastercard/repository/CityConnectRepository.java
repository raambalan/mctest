package com.raambalan.mastercard.repository;

import com.raambalan.mastercard.model.CityPathGraph;
import java.io.IOException;

public interface CityConnectRepository {
     CityPathGraph loadPaths(String fileName) throws IOException;
}
