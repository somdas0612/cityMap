This  is a microservice developed using spring boot which helps to determine if the road exist between two city.
Ihis API should be able to handle BFS of the city which means if there is N number of city between origin and destination it will traverse
all the city between origin and destination and finds if they are connected.

To Run this project follow the steps below.

1. Check out the code from git repo.
2. Build the code using maven
3. run Application.java as Java application or mvn spring-boot:run (do not use maven to run application)

Use the following url to test the application.


http://localhost:8080/connected?origin=Boston&destination=Newark
Should return yes

http://localhost:8080/connected?origin=Boston&destination= Philadelphia
Should return yes

http://localhost:8080/connected?origin=Philadelphia&destination=Albany
Should return no

http://localhost:8080/connected?origin=New%20York&destination=Newark
Should return yes

http://localhost:8080/connected?origin=&destination=Charlotte
should return "Origin City is required"

P.S: currently the city list is provided through flat file, this can be read from external interface. To Make this problem little complex I have
added couple of city pair in city.txt. With that new addition of city pair the following url should return Yes.
http://localhost:8080/connected?origin=Boston&destination=Dallas
should return Yes.

To develope this service we have used most of SOLID design priciple.

Due to the time constraint I could not add other testcase, but I will highly recommend to add JUNIT for service class.