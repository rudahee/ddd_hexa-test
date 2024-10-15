
# Task Management App

This repository contains an application where you can manage tasks. 
In addition, in this repository you will find everything you need to run it.

## Prerequisites

Before you begin, ensure you have the following installed:

- Java Development Kit (JDK)
- MongoDB
- Git
- Some REST Client (Bruno is my recommendation)
- Some IDE for Java (Jetbrains bundle is my choice)

## Setup Instructions

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

## Project Structure

The project is structured following the Hexagonal Architecture and DDD pattern:

- `task-manager-application`: Contains application services and use cases
- `task-manager-domain`: Contains the core domain logic
- `task-manager-infrastructure`: Contains adapters for external services and repositories

## Running the Application

To run the application once compiled, use the following command:
```
java -jar task-manager-infrastructure/target/task-manager-infrastructure-1.0-SNAPSHOT.jar
```
or using script on `resources\scripts` folder.

## Testing

To run the tests, execute:

```
mvn test
```

## Usage

The application likely provides RESTful endpoints for task management. However, specific endpoints and their usage 
are not detailed in this readme. You can check all endpoints on `resources\api` on OpenAPI yaml or postman collection.

## Additional Notes

- This project serves as a demonstration of DDD (Domain-Driven Design) and Hexagonal Architecture principles in Java.
- The use of Spring Boot is evident from the `pom.xml` file, indicating that this is a Spring Boot application.
- For more detailed information about the project structure and implementation details, 
refer to the source code and any additional documentation within the repository.

