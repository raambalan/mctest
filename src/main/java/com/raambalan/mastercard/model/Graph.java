package com.raambalan.mastercard.model;

public interface Graph {
    enum GraphType {
        DIRECTED,
        UNDIRECTED
    }
    enum ResultType {
        PATH_FOUND,
        NO_PATH_FOUND,
        SOURCE_NOT_FOUND,
        DEST_NOT_FOUND
    };
    GraphType getGraphType();
    void addPath(String source,String dest);
    City getCity(String name);

}
