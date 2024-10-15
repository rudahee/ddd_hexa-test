
# Task Management App.

This guide will help you set up and use the DDD Hexagonal Architecture test application from the [rudahee/ddd_hexa-test](https://github.com/rudahee/ddd_hexa-test) GitHub repository.

## Prerequisites

Before you begin, ensure you have the following installed:
- Java Development Kit (JDK)
- Maven
- Git

## Setup Instructions

1. Clone the repository:
   ```
   git clone https://github.com/rudahee/ddd_hexa-test.git
   ```

2. Navigate to the project directory:
   ```
   cd ddd_hexa-test
   ```

3. Build the project using Maven:
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

```
mvn 
```

## Testing

To run the tests, execute:

```
mvn test
```

## Usage

The application likely provides RESTful endpoints for task management. However, specific endpoints and their usage are not detailed in the repository. You may need to explore the codebase or check for any available API documentation.

## Contributing

If you wish to contribute to this project:

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Additional Notes

- This project serves as a demonstration of DDD (Domain-Driven Design) and Hexagonal Architecture principles in Java.
- The use of Spring Boot is evident from the `pom.xml` file, indicating that this is a Spring Boot application.
- For more detailed information about the project structure and implementation details, refer to the source code and any additional documentation within the repository.

