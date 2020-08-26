package com.raambalan.mastercard.service;

import com.raambalan.mastercard.model.Graph;

import java.io.IOException;

public interface CityConnectService {
    Graph.ResultType findPath(String source, String destination);
    default void loadPaths() throws IOException {
        loadPaths("city.txt");
    }
    void loadPaths(String path) throws IOException;
}
