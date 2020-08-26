package com.raambalan.mastercard.controller;

import com.raambalan.mastercard.service.CityConnectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.raambalan.mastercard.model.Graph.ResultType;
@Api(value = "CityConnect Controller", description = "REST Apis that checks whether there is a path between the cities")
@RestController
@RequestMapping(value = "/")
public class CityConnectController {
    private CityConnectService connectService;

    public CityConnectController(CityConnectService connectService) throws IOException {
        this.connectService = connectService;
        this.connectService.loadPaths();
    }
    @ApiOperation(value = "Finds paths  between origin and destination", response = Iterable.class, tags = "findPath")
    @GetMapping(value = "/connected")
    String findPath(@RequestParam(value = "origin", defaultValue = "") String origin,
                    @RequestParam(value = "destination", defaultValue = "") String destination) {
        ResultType resultType = connectService.findPath(origin, destination);
        if (resultType.equals(ResultType.PATH_FOUND))
            return "Yes";
        else
            return "No";
    }

}
