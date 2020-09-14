# stocksapp
Stocks Application with REST API

Prerequisite

Java 8
PostgreSQL DB setup:
url: jdbc:postgresql://localhost:5432/stocksappdb
username: stocksappuser
password: stocksappuser123

Build

mvn clean install

Run

mvn spring-boot:run

Assumptions:

1. User authentication is not implemented and done outside of the scope of this module
2. Functional tesing is done using Postman app
3. Bulk upload is implemented using Apache Commons CSV 
 
Endpoint to test the service: localhost:8080
1. Bulk upload request - POST
localhost:8080/api/djstock/bulkupload
request parameter 1: setupByUserId=1 
request parameter 2: file=dow_jones_index.data (file) 

2. Get list of stockes - GET
localhost:8080/api/djstock/AA
request parameter 1: setupByUserId=1 

3. Add new resord - POST
localhost:8080/api/djstock/create
request parameter 1: setupByUserId=1
request body:
{
    "quarter": 2,
    "stockSymbol": "CCC",
    "lastBusDate": "2012-10-10",
    "openPrice": 12,
    "closePrice": 14
}

