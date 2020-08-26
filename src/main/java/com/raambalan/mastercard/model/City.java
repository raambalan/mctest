package com.raambalan.mastercard.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
public class City {

    private String name;
    private Set<String> adjCities;

    public City(String name) {
        this.name = name;
        this.adjCities = new HashSet<>();
    }

    public void addEdge(String v) {
        adjCities.add(v);
    }

    public List<String> getAdjacentCities() {
        return new ArrayList(adjCities);
    }
}
