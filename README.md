# City Connect
Spring Boot App to test connection route between given source and destination cities via REST endpoint.
This application loads default available paths between cities from `resources\city.txt`
# Installation(on windows)
1. Clone the source from `git clone https://github.com/raambalan/mctest.git`
2. Run application in command line using `mvnw.cmd clean springboot:run`
3. Test cases may be run via `mvnw.cmd clean test` 

# Rest endpoint
Rest endpoint available at `http://localhost:8080/connected`. 

The endpoint `http://localhost:8080/connected?origin=Philadelphia&destination=Boston` will return `Yes` 
if the connection route exist between given cities. Otherwise, it returns `No`

# Design
Follows spring  MVC pattern. The CitiConnectController implements Rest Endpoint. It depends on CityConnectService 
that implements `findPath' between given source and destination cities.
Data loading handled in CityConnectRepository on initialization from  city.txt under resource directory.

 The sample city.txt  with header below 
 ```source,destination
 Source,Destination
 Boston, New York
 Philadelphia, Newark
 Newark, Boston
 Trenton, Albany
```
# Software requirement:
1. Java 1.8
2. Maven

# Endpoint
Rest Endpoint detail available at http://localhost:8080/swagger-ui.html#/