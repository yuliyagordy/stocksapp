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
 
