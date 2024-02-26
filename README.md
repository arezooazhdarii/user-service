# Assignment User Service

## Purpose

The purpose of this program is to create an application to manage user location.

## Building and deploying the application

### Testing the application

To run test cases of the project execute the following command:

```bash
  mvn test
```

### Running the application

### Database
To see database records and tables you can check [Adminer panel](http://localhost:8080/h2-console/) ```http://localhost:8080```
```text
 user : root
 password : root
```
## Solution

### 1. save or update user data:
```text
 save   (POST): /api/v1/users
 update (PUT) : /api/v1/users/{id}
```
* I developed two separate services we can see the endpoints on UserController.
  It can be a service for both and checked with the user's email or other unique information related to the business.

### 2. get user data with the latest location (if exists) by userId and response should be:

```text
 getLatestLocation (GET): /api/v1/locations/{id}
```

* Created an endpoint on LocationController for get the latest user location we get userId from path variable.

### 3. get user locations by date time range and response should be:
```text
 getLocationsByDateTimeRange  (GET):/api/v1/locationsByDate/{id}
```
* Created an endpoint on LocationController that get userId from path variable and get two Date from client. 


### save user location:
```text
 save  (POST):/api/v1/locations
```
* This endpoint allows save user location.




## Technology stack & other Open-source libraries

### Data

* [H2 Database Engine](https://www.h2database.com/html/main.html) - Java SQL database. Embedded and server modes;
  in-memory databases

### Server - Backend

* [JDK 17](https://www.oracle.com/java/technologies/javase/jdk11-readme.html) - Java™ Platform, Standard Edition
  Development Kit
* [Spring Boot 2.7.4](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new
  Spring Applications
* [Maven](https://maven.apache.org) - Maven is a build automation tool used primarily for Java projects

### Libraries and Plugins

* [Lombok](https://projectlombok.org/) - Never write another getter or equals method again, with one annotation your
  class has a fully featured builder, Automate your logging variables, and much more.

* [Spring Rest Doc](https://spring.io/projects/spring-restdocs) - Spring REST Docs generates documentation for RESTful
  services that is both accurate and readable.
* [MapStruct](https://mapstruct.org) - MapStruct is a code generator that greatly simplifies the implementation of mappings between Java bean types

## Documentation

* [Swagger](http://localhost:8083/swagger-ui/index.html) - Documentation & Testing