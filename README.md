# Bankify API

Bankify API is a RESTful web service that provides banking functionality, including account management and transactions. This API is designed for use by both web and mobile clients to interact with the banking system.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Endpoints](#endpoints)
- [Authentication](#authentication)
- [Error Handling](#error-handling)
- [Contributing](#contributing)
- [License](#license)

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 11 or higher
- Spring Boot 2.5 or higher
- MySQL or another relational database
- Maven for building the project

## Getting Started

1. Clone this repository to your local machine:

   bash
   git clone https://github.com/your-username/bankify-api.git
   

2. Configure your database connection by editing the application.properties file in the src/main/resources directory:

   properties
   spring.datasource.url=jdbc:mysql://localhost:3306/bankify
   spring.datasource.username=root
   spring.datasource.password=your-password
   

3. Build and run the project:

   bash
   mvn spring-boot:run
   

The API will be available at http://localhost:8080.

## Endpoints

### Accounts

- POST /api/accounts: Create a new bank account.
- GET /api/accounts/{accountId}: Get account details by account ID.

### Authentication

- POST /api/auth/login: Authenticate and obtain an authentication token.

### Transactions

- POST /api/transactions/transfer: Make a fund transfer between accounts.
- GET /api/transactions/: Get transaction history for the authenticated user.

### Users

- POST /api/user/register: Register a new user.
- GET /api/user/profile: Get the user's profile information.

## Authentication

To access protected endpoints, you must include an authentication token in the request headers. Obtain an authentication token by sending a POST request to /api/auth/login with valid login credentials.

Example:

http
POST /api/auth/login
Content-Type: application/json

{
  "username": "your-username",
  "password": "your-password"
}


The response will contain an authentication token that should be included in the Authorization header for subsequent requests.

Example:

http
GET /api/accounts/1
Authorization: Bearer your-auth-token


## Error Handling

The API returns standard HTTP status codes and error responses in JSON format. Error responses include an error field with a human-readable error message.

Example error response:

json
{
  "error": "Account not found."
}


## Contributing

Contributions are welcome! If you'd like to contribute to this project, please follow the standard GitHub fork and pull request workflow.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
