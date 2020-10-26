## Stocks Application with REST API

### Description

There is a collection of records from the [Dow Jones Index from 2011](http://archive.ics.uci.edu/ml/datasets/Dow+Jones+Index#) 
The perpouse of this project is to build a Spring Boot application server that would allow users to perform the following operations:

1. Upload a bulk data set - you can find data (dow_jones_index.data) file in the directory */data
2. Query for data by stock symbol like "AA", "AXP", "BA", "BAC" and others that you can find in the data file
3. Add a new record

### Assumptions:

1. User authentication is not implemented and done outside of the scope of this module
2. Functional tesing is done using Postman app
3. Bulk upload is implemented using Apache Commons CSV 
 
### Prerequisite

Java 8
PostgreSQL DB setup:
* url: jdbc:postgresql://localhost:5432/stocksappdb
* username: stocksappuser
* password: stocksappuser123

### Build

mvn clean install

###  Testing

Automated tests for Functions of Controller are done using Mockito framework

### Run

mvn spring-boot:run
 
Endpoint to test the service: localhost:8080
1. Bulk upload request - POST
  * localhost:8080/api/djstock/bulkupload
  * request parameter 1: setupByUserId=1 
  * request parameter 2: file=dow_jones_index.data (file) 

2. Get list of stockes - GET
  * localhost:8080/api/djstock/AA
  * request parameter 1: setupByUserId=1 

3. Add new resord - POST
  * localhost:8080/api/djstock/create
  * request parameter 1: setupByUserId=1
  * request body:
{
    "quarter": 2,
    "stockSymbol": "CCC",
    "lastBusDate": "2012-10-10",
    "openPrice": 12,
    "closePrice": 14
}

