# Retail Discount Service

###### Run the application
1- Clone the repo to your machine.

2- From command line open the project directory and run `mvn spring-boot:run`

3- open http://localhost:8080/invoice.html.


###### Run test & reports
1- For unit test & jacoco report
 ~~~~
mvn test
~~~~
you can find jacoco report in _target/site/jacoco_

2- For SonarCube report
~~~~
mvn verify sonar:sonar
~~~~
you can find the report here: https://sonarcloud.io/dashboard?id=salahqasem_Retail


###### Add new records to database
you can add new data to the database by to approaches:

1- add insert statements to _data.sql_ file inside _resources_ directory

2- or you can doing that using the H2 database console, 
after starting the application, open the H2 console. http://localhost:8080/h2/ and use the following properties.

~~~~
JDBC URL: jdbc:h2:mem:retailDB
User Name: retail
~~~~
_Note: keep password empty_

after that you can fill the data using a GUI, but keep in your mind we are using H2 as in memory DB, so data will not be available after restarting the app.

you can add items to the `Item` table, and use this items in the `invoice.html` page.