
# Task Management App



## Index

1. **[Intro](#intro)**
2. **[Prerequisites](#prerequisites)**
3. **[Setup Instructions](#setup-instructions)**
   1. Project Setup
   2. Database Setup
   3. REST API Client Setup
4. **[Project Structure](#project-structure)**
5. **[Running The Application](#running-the-application)**
6. **[Testing](#testing)**
7. **[Usage](#usage)**
8. **[Additional Notes](#additional-notes)**
---

## Intro

This repository contains an application where you can manage tasks. 
In addition, in this repository you will find everything you need to run it.

---

## Prerequisites

Before you begin, ensure you have the following installed:

- Java Development Kit (JDK)
- MongoDB
- Git
- Some REST Client (Bruno is my recommendation)
- Some IDE for Java (Jetbrains bundle is my choice)

---

## Setup Instructions

1. **Project Setup**
   1. Clone the repository:
      ```
      git clone https://github.com/rudahee/ddd_hexa-test.git
      ```

   2. Navigate to the project directory:
      ```
      cd ddd_hexa-test
      ```

   3. Build the project using Maven (or using IDE GUI):
      ```
      mvn clean install
      ```

2. **Database Setup**
   1. Install the latest version of MongoDB through this official tutorial:
      ```ini
      [Windows]
      https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-windows/
      ```
      ```ini
      [Linux]
      https://www.mongodb.com/docs/manual/administration/install-on-linux/
      ```
   2. Load the data from command line into MongoDB through the file in the [resources folder](https://github.com/rudahee/ddd_hexa-test/blob/master/resources/db/taskDB.task.json).
      ```
      mongoimport --db taskDB --collection task --file resources/taskDB.task.json
      ```
3. **REST API Client Setup**
   - **Postman**
     1. Import Postman exported JSON via Postman GUI 
     2. Add `baseUrl` env variable with `http://localhost:8080` value 

   - **Bruno**
     1. Import OpenAPIv3 JSON via Bruno GUI 
     2. Add `baseUrl` env variable with `http://localhost:8080` value 

----

## Project Structure

The project is structured following the Hexagonal Architecture and DDD pattern:

- `task-manager-application`: Contains application services and use cases
- `task-manager-domain`: Contains the core domain logic
- `task-manager-infrastructure`: Contains adapters for external services and repositories

----

## Running the Application

To run the application once compiled you can run it.

- Use the following command:
```
java -jar task-manager-infrastructure/target/task-manager-infrastructure-1.0-SNAPSHOT.jar
```

- Using script on `resources\scripts` folder.
- Or just using your IDE GUI.

----

## Testing

To run the tests, execute:

```
mvn test
```

-----

## Usage

The application likely provides RESTful endpoints for task management. However, specific endpoints and their usage 
are not detailed in this readme. 

You can check all endpoints on `resources\api` on OpenAPI yaml or postman collection.

-----

## Additional Notes

- This project serves as a demonstration of DDD (Domain-Driven Design) and Hexagonal Architecture principles in Java.
- The use of Spring Boot is evident from the `pom.xml` file, indicating that this is a Spring Boot application.
- For more detailed information about the project structure and implementation details you can contact with me on [my personal mail](mailto:jdazher@hotmail.com).

