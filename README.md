# Spring Boot Example Project for "Book Management" in Library

This is a sample Java / Maven / Spring Boot (version 2.5.1) application that serves the following functionality -
* fetch all the available genres (book family like 'fantasy') & insert / update / delete the genre
* fetch, insert, update, delete a book
* generate an excel export for collection on books available in system.


## How to Run

This application is packaged as a jar which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.
Or you can run the application with your favourite IDE as Spring Boot App.

* Clone this repository
* Make sure you are using JDK 11 or above and Maven 3.x
* You can build the project by running ```mvn clean install```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar target/library-mgmt-0.0.1-SNAPSHOT.jar
or
        mvn spring-boot:run
```
Once the application runs you should see something like this -

```
[0;39m [36mo.s.b.w.embedded.tomcat.TomcatWebServer [0;39m [2m:[0;39m Tomcat started on port(s): 8080 (http) with context path ''
[0;39m [2m[           main][0;39m [36mc.l.library.LibraryMgmtApplication      [0;39m [2m:[0;39m Started LibraryMgmtApplication in 2.75 seconds (JVM running for 3.149)
```
* The repository contains a POSTMAN collection - ``` LIBRARY-MANAGEMENT.postman_collection ```, that can be imported.
* In order to use the services you first need to authenticate yourself with the ```username = 'lampiris' and password = 'lampiris' ``` via requesting to endpoint
```
http://localhost:8081/api/librarymanagement/authenticate
```
* Upon successful authentication it will return JSON web token in a response header as ``` access-token ```.
* Other endpoints will require this access token in request header provided as ``` Authorization = Bearer {{access-token}} ```, else the request will rejected with ```403 - Forbidden```
* Supports both XML and JSON response; simply use desired Accept header in your request.
* You can export all the book collection upon requesting a endpoint on any browser
```
http://localhost:8080/api/librarymanagement/book/export
```

## Run with Docker

* Docker image can be build using command ``` docker build -f Dockerfile -t docker-library-mgmt . ```  (use ```.``` at end if you are in same directory else provide the path)
* Run the image in container by running the command ``` docker run -p 8081:8080 docker-library-mgmt ``` (You can choose the port you want to map, application configure to run in ```port=8080```)
* It is already publish to Docker Hub. In order to pull the image from Docker hub use command ``` docker pull sanvesh0402/docker-library-mgmt ```

## API Documentation

* All APIs are "self-documented" by Swagger2 using annotations. In order to generate swagger file please use the endpoint.
```
http://localhost:8080/v2/api-docs
```
* You can copy the contents of the above service response and visualise all the endpoints at ```https://editor.swagger.io``` as HTML.
```
