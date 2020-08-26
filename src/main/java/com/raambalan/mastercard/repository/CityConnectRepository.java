package com.raambalan.mastercard.repository;

import com.raambalan.mastercard.model.Graph;

import java.io.IOException;

public interface CityConnectRepository {
    Graph loadPaths(String fileName) throws IOException;
}
