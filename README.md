# Client Service Application

## Description
This is a Spring Boot application designed for managing client information. It provides functionalities to create and retrieve clients, as well as calculate their age from their birthdate.

## Features
- Creation of client records.
- Retrieval of clients by ID.
- Calculation of clients' age.

## Technologies Used
- Spring Boot
- JPA / Hibernate
- Maven
- JUnit and Mockito for testing

## How to Run
1. Clone the repository:
   ```bash
   git clone [repository URL]
   mvn spring-boot:run

### The application will be available at http://localhost:8080.

## API Endpoints
- POST /api/clients/ - Create a new client.
- GET /api/clients/{id} - Retrieve details of a specific client by ID.

## Configuration
Configure database connection and other properties in src/main/resources/application.properties.

## Contributing
- To contribute to this project, please create a new branch and submit a Pull Request with your changes for review.
