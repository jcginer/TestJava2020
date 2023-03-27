# TestJava2020

## Generate jar:
- Download project and execute in a cmd shell from project directory, the following command: mvn clean install

## Execute jar:
- Execute in a cmd shell from project directory: mvn spring-boot:run

## Docker:
- In the root path execute:
docker build -t spring-boot-docker:test-java-2020 .
docker run -p 8080:8080 spring-boot-docker:test-java-2020 .

## General comments:
- Java 11
- Maven: 3.8.1 (Central repository)
- Spring: 2.7.10
- Port used: 8080
- Test the application:
- Swagger:
  http://localhost:8080/swagger-ui.html
## Additional Modifications:
- Database dates formats.
- In database table, I would add a column with the final price
- It can be added CRUD functionality in the controller.
- ...

