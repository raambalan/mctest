package com.raambalan.mastercard.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
public class City {
    @ApiModelProperty(notes = "Name of the City",name="name",required=true)
    private String name;
    private Set<String> adjCities;

    public City(String name) {
        this.name = name;
        this.adjCities = new HashSet<>();
    }

    public void addEdge(String v) {
        adjCities.add(v);
    }


}
