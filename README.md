﻿# TestJava2020

## Execute (jar):
- Download project and execute in a cmd shell from project directory, the following command: mvn clean install
- Execute in a cmd shell from project directory: mvn spring-boot:run

## Execute (Docker):
- Download project.
- In the root path execute:
  * docker build -t spring-boot-docker:test-java-2020 .
  * docker run -p 8080:8080 spring-boot-docker:test-java-2020 .
![image](https://user-images.githubusercontent.com/944486/227950365-a0dc6b07-b0e1-43db-93bb-21bbe10bc0b0.png)


## General comments:
- Java 11
- Maven: 3.8.1 (Central repository)
- Spring: 2.7.10
- Port used: 8080
- Database: H2 (in memory)
- Swagger:
  http://localhost:8080/swagger-ui.html
- Test the application:
  Open a Postman (for example) and execute the following POSTs:
  ![img.png](img.png)
  ![img_2.png](img_2.png)
  
  (No additional headers required)

- Sonar and coverage run.

## Additional improvements could be done:
- Database dates formats.
- In database table, I would add a column with the final price
- It can be added CRUD functionality in the controller.
- ...

