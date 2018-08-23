This project has been done using java 1.6 since I could not upgrade my java version to java 1.8.
This  is a microservice developed using spring boot which helps to determine if the road exist between two city.
To Run this project follow the steps below.

1. Check out the code from git repo.
2. Build the code using maven
3. run Application.java as Java application or mvn spring-boot:run (do not use maven to run application)

Use the following url to test the application.

http://localhost:8080/connected/Philadelphia/Albany
http://localhost:8080/connected/Boston/Newark
http://localhost:8080/connected/Boston/Philadelphia
http://localhost:8080/connected/New%20York/Newark


P.S: currently the city list is provided through flat file, this can be read from external interface.

To develope this service we have used most of SOLID design priciple.

Due to the time constraint I could not add other testcase, but I will highly recommend to add JUNIT for service class.