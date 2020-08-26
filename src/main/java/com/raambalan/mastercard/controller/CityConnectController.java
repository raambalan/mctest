package com.raambalan.mastercard.controller;

import com.raambalan.mastercard.CityConnectQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.raambalan.mastercard.model.Graph.ResultType;

@RestController
public class CityConnectController {
    private CityConnectQuery connectQuery;

    public CityConnectController(CityConnectQuery connectQuery) throws IOException {
        this.connectQuery = connectQuery;
        this.connectQuery.loadPaths();
    }

    @RequestMapping(value = "/connected")
    String findPath(@RequestParam(value = "origin", defaultValue = "") String origin,
                    @RequestParam(value = "destination", defaultValue = "") String destination) {
        ResultType resultType = connectQuery.findPath(origin, destination);
        if (resultType.equals(ResultType.PATH_FOUND))
            return "Yes";
        else
            return "No";
    }

}
