# Client Service Application

## Description
The Commerce Application is designed to streamline the operations of a business by focusing on three key actors: the customer, the products, and the sales transactions. This application automates several critical aspects of business operations, including inventory management and sales documentation.

## Core Functionalities
1. **Inventory Management:** Automatically adjusts the stock levels of items upon each sale.
2. **Sales Documentation:** Generates detailed sales receipts, including:
- Date of sale (sourced from an external web service).
- Customer information.
- List of purchased products with their attributes.
- Total sale amount.
- Updated stock levels displayed post-transaction.

## REST Services
To support these functionalities, the following REST services are integrated:

1. **Sales Receipt Creation:** Generates a comprehensive sales receipt for each transaction.
2.  **Product Management:** Enables creation and updating of product details.
    - Product prices can be updated unless the product is part of an ongoing sale.
3. **Customer Management:** Facilitates the creation and updating of customer information.

## REST API Status Codes
- **200 OK:** Indicates successful operation (e.g., retrieval or update).
Example: Returning customer details when the customer exists.
- **409 Conflict:** Implies the requested operation cannot be executed (e.g., retrieval or update is not possible).
Example: Updating non-existent customer information.

## Technologies Used
- Java SE 21
- Spring Boot
- JPA / Hibernate
- Maven
- JUnit and Mockito for testing
- Swagger

## How to Run
1. Clone the repository:
   ```bash
   git clone [repository URL]
   mvn spring-boot:run

##### The application will be available at http://localhost:9500

## API Documentation
Access comprehensive API documentation at: http://localhost:9500/swagger-ui/index.html

This update provides a detailed markdown overview of the application's key features, functionalities, REST services, and the usage of HTTP status codes for efficient API interaction.

## Configuration
Configure database connection and other properties in src/main/resources/application.properties.

## Contributing
- To contribute to this project, please create a new branch and submit a Pull Request with your changes for review.
