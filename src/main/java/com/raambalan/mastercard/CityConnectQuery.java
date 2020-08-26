package com.raambalan.mastercard;

import com.raambalan.mastercard.model.Graph;

import java.io.IOException;

public interface CityConnectQuery {
    Graph.ResultType findPath(String source, String destination);
    default void loadPaths() throws IOException {
        loadPaths("city.txt");
    }
    void loadPaths(String path) throws IOException;
}
