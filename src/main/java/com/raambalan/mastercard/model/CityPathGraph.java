package com.raambalan.mastercard.model;

import java.util.HashMap;
import java.util.Map;

public class CityPathGraph implements Graph {
    private Map<String, City> cityMap;

    public CityPathGraph() {
        cityMap = new HashMap();
    }

    @Override
    public GraphType getGraphType() {
        return GraphType.UNDIRECTED;
    }

    @Override
    public void addPath(String source, String dest) {
        cityMap.putIfAbsent(source, new City(source));
        cityMap.putIfAbsent(dest, new City(dest));

        City sourceCity = cityMap.get(source);
        sourceCity.addEdge(dest);
        if(getGraphType().equals(GraphType.UNDIRECTED)) {
            City destCity = cityMap.get(dest);
            destCity.addEdge(source);
        }
    }

    public City getCity(String name) {
        return cityMap.getOrDefault(name,new City("CityNotfound"));
    }
}
