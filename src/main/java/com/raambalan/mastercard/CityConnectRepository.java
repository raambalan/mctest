package com.raambalan.mastercard;

import com.raambalan.mastercard.model.CityPathGraph;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Repository
@Slf4j
public class CityConnectRepository {

    private void readFromInputStream(InputStream inputStream, CityPathGraph cityPathGraph)
            throws IOException {
        //Create the CSVFormat object
        CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
        //initialize the CSVParser object
        CSVParser parser = new CSVParser(new InputStreamReader(inputStream), format);
        log.info("Loading paths");
        for (CSVRecord record : parser) {
            String source = record.get(0);
            String destination = record.get(1);
            log.info("Path : Origin:{} and Destination:{}", source, destination);
            cityPathGraph.addPath(source.trim(), destination.trim());
        }
    }

    public CityPathGraph loadPaths(String fileName) throws IOException {
        CityPathGraph cityPathGraph = new CityPathGraph();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        readFromInputStream(inputStream, cityPathGraph);
        return cityPathGraph;
    }
}
