# 💰 Ledgerly - Expense Tracking REST API

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=for-the-badge)
![JUnit 5](https://img.shields.io/badge/JUnit-5-success?style=for-the-badge)
![Docker](https://img.shields.io/badge/Docker-Ready-blue?style=for-the-badge)

*A clean, lightweight and extensible Expense Tracking REST API built using Java and Spring Boot.*

</div>

---

# 📖 Overview

Ledgerly is a RESTful Expense Tracking API developed using **Java**, **Spring Boot**, and **Maven**.

The project allows users to:

- Record daily expenses
- Retrieve all expenses
- Filter expenses by category
- Search expenses
- Generate overall expense summaries
- Generate monthly expense summaries
- Delete existing expenses

To keep the assessment focused on API design and business logic, the application uses an **in-memory HashMap** instead of a database.

Although persistence is intentionally omitted, the project follows a clean layered architecture and production-style coding practices, making it easy to migrate to a relational database in the future.

---

# 📋 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Architecture](#-project-architecture)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
- [Running with Docker](#-running-with-docker)
- [Running Tests](#-running-tests)
- [API Documentation](#-api-documentation)
- [Optional Bonus Features Implemented](#-optional-bonus-features-implemented)
- [Design Decisions](#-design-decisions)
- [Testing Strategy](#-testing-strategy)
- [Assumptions](#-assumptions)
- [Limitations](#-limitations)
- [Future Improvements](#-future-improvements)
- [Build Commands](#-build-commands)
- [Author](#-author)

---

# ✨ Features

### Core Features

- ✅ Add a new expense
- ✅ View all expenses
- ✅ Delete an expense
- ✅ Filter expenses by category
- ✅ Overall expense summary
- ✅ Monthly expense summary

### Additional Features

- 🔍 Search expenses by title or category
- 📊 Category-wise expense summary
- ✔ Request validation using Jakarta Validation
- ⚠ Global Exception Handling
- 📦 Standardized API Response Wrapper
- 🧪 Unit Testing (Service + Controller)
- 🐳 Docker Support

---

# 🛠 Tech Stack

| Technology | Version |
|------------|---------|
| Java | 21 |
| Spring Boot | 3.5.6 |
| Maven | Latest |
| JUnit | 5 |
| Mockito | Latest |
| Docker | Latest |
| Lombok | Latest |

---

# 🏗 Project Architecture

The project follows a layered architecture.

```
Client
   │
   ▼
Controller
   │
   ▼
Service Interface
   │
   ▼
Service Implementation
   │
   ▼
In-Memory Storage (HashMap)
```

---

# 📂 Project Structure

```
ledgerly
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.sooumik.ledgerly
│   │   │
│   │   ├── advice
│   │   │      ├── ApiResponse
│   │   │      ├── ErrorResponse
│   │   │      ├── GlobalExceptionHandler
│   │   │      └── GlobalResponseHandler
│   │   │
│   │   ├── controller
│   │   │      └── ExpenseController
│   │   │
│   │   ├── dto
│   │   │      ├── request
│   │   │      └── response
│   │   │
│   │   ├── exception
│   │   │      ├── ResourceNotFoundException
│   │   │      └── DuplicateResourceFoundException
│   │   │
│   │   ├── model
│   │   │      └── Expense
│   │   │
│   │   ├── service
│   │   │      ├── ExpenseService
│   │   │      └── impl
│   │   │             └── ExpenseServiceImpl
│   │   │
│   │   └── LedgerlyApplication
│   │
│   └── resources
│
├── src/test
│   ├── controller
│   └── service
│
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

# ⚙ Prerequisites

Ensure the following software is installed:

- Java 21
- Maven
- Git
- Docker *(Optional)*

Verify installation:

```bash
java -version
```

```bash
mvn -version
```

```bash
docker --version
```

---

# 🚀 Getting Started

## 1. Clone the Repository

```bash
git clone https://github.com/<your-username>/ledgerly.git
```

---

## 2. Navigate to the Project

```bash
cd ledgerly
```

---

## 3. Build the Project

```bash
mvn clean install
```

---

## 4. Run the Application

Using Maven

```bash
mvn spring-boot:run
```

or

```bash
java -jar target/ledgerly-0.0.1-SNAPSHOT.jar
```

---

# 🌐 Base URL

```
http://localhost:8080/api/v1
```

---

# 🐳 Running with Docker

## Build Docker Image

```bash
docker build -t ledgerly .
```

---

## Run Container

```bash
docker run -p 8080:8080 ledgerly
```

---

## Using Docker Compose

```bash
docker compose up --build
```

The application will be available at:

```
http://localhost:8080
```

To stop the container:

```bash
docker compose down
```

---

# 🧪 Running Tests

Execute all unit tests.

```bash
mvn test
```

Current test coverage includes:

- ✓ 10 Service Layer Tests
- ✓ 7 Controller Layer Tests
- ✓ 1 Application Context Test

**Total: 18 automated tests**

---

# 📚 API Documentation

## HTTP Status Codes

| Code | Meaning |
|------|---------|
| 200 | OK |
| 201 | Created |
| 400 | Bad Request |
| 404 | Not Found |

## Response Format

Every successful API response follows the structure below:

```json
{
    "timestamp": "2026-08-02T14:20:30",
    "data": {},
    "message": "Request processed successfully",
    "error": null
}
```

Every error response follows the structure below:

```json
{
    "timestamp": "2026-08-02T14:20:30",
    "data": null,
    "message": "Validation Failed",
    "error": {
        "status": 400,
        "message": "...",
        "subErrors": []
    }
}
```

---

# 📌 API Overview

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/expenses` | Add Expense |
| GET | `/expenses` | View All Expenses |
| GET | `/expenses/category/{category}` | Filter by Category |
| GET | `/expenses/search/{keyword}` | Search Expenses |
| GET | `/expenses/summary` | Overall Expense Summary |
| GET | `/expenses/summary/{year}/{month}` | Monthly Expense Summary |
| DELETE | `/expenses/{expenseId}` | Delete Expense |

---

# 1️⃣ Add Expense

Creates a new expense entry.

## Endpoint

```http
POST /api/v1/expenses
```

## Request Body

```json
{
    "title": "Pizza",
    "amount": 499.99,
    "category": "Food",
    "date": "2026-08-01"
}
```

## Success Response

**HTTP 201 Created**

```json
{
    "timestamp": "...",
    "data": {
        "id": 1,
        "title": "Pizza",
        "amount": 499.99,
        "category": "Food",
        "date": "2026-08-01"
    },
    "message": "Expense created successfully",
    "error": null
}
```

## Validation Rules

| Field | Validation |
|--------|------------|
| title | Must not be blank |
| amount | Must not be null |
| category | Must not be blank |
| date | Must not be null |

## Possible Status Codes

| Status | Description |
|---------|-------------|
| 201 | Expense Created |
| 400 | Validation Failed |

---

# 2️⃣ Get All Expenses

Returns all available expenses.

## Endpoint

```http
GET /api/v1/expenses
```

## Success Response

**HTTP 200 OK**

```json
{
    "timestamp": "...",
    "data": [
        {
            "id": 1,
            "title": "Pizza",
            "amount": 499.99,
            "category": "Food",
            "date": "2026-08-01"
        },
        {
            "id": 2,
            "title": "Netflix",
            "amount": 649.00,
            "category": "Entertainment",
            "date": "2026-08-02"
        }
    ],
    "message": "Request processed successfully",
    "error": null
}
```

### Empty List

```json
{
    "timestamp": "...",
    "data": [],
    "message": "No expenses found",
    "error": null
}
```

## Status Codes

| Status | Description |
|---------|-------------|
| 200 | Success |

---

# 3️⃣ Filter Expenses by Category

Returns all expenses belonging to a specific category.

## Endpoint

```http
GET /api/v1/expenses/category/{category}
```

## Example

```http
GET /api/v1/expenses/category/Food
```

## Success Response

```json
{
    "timestamp": "...",
    "data": [
        {
            "id": 1,
            "title": "Pizza",
            "amount": 499.99,
            "category": "Food",
            "date": "2026-08-01"
        }
    ],
    "message": "Request processed successfully",
    "error": null
}
```

## Error Response

```json
{
    "timestamp": "...",
    "data": null,
    "message": "Resource not found",
    "error": {
        "status": 404,
        "message": "No expenses found for category: Food",
        "subErrors": []
    }
}
```

## Status Codes

| Status | Description |
|---------|-------------|
| 200 | Success |
| 404 | Category Not Found |

---

# 4️⃣ Search Expenses

Searches expenses by title or category.

## Endpoint

```http
GET /api/v1/expenses/search/{keyword}
```

## Example

```http
GET /api/v1/expenses/search/Pizza
```

## Success Response

```json
{
    "timestamp": "...",
    "data": [
        {
            "id": 1,
            "title": "Pizza",
            "amount": 499.99,
            "category": "Food",
            "date": "2026-08-01"
        }
    ],
    "message": "Request processed successfully",
    "error": null
}
```

## Error Response

```json
{
    "timestamp": "...",
    "data": null,
    "message": "Resource not found",
    "error": {
        "status": 404,
        "message": "No expenses found matching: Pizza",
        "subErrors": []
    }
}
```

---

# 5️⃣ Overall Expense Summary

Returns the total expenses and category-wise expense breakdown.

## Endpoint

```http
GET /api/v1/expenses/summary
```

## Success Response

```json
{
    "timestamp": "...",
    "data": {
        "totalExpenses": 2648.99,
        "categoryWiseExpenses": {
            "Food": 1499.99,
            "Entertainment": 649.00,
            "Travel": 500.00
        }
    },
    "message": "Request processed successfully",
    "error": null
}
```

## Error Response

```json
{
    "timestamp": "...",
    "data": null,
    "message": "Resource not found",
    "error": {
        "status": 404,
        "message": "No expenses found to generate summary.",
        "subErrors": []
    }
}
```

---

# 6️⃣ Monthly Expense Summary

Returns the total expenses and category-wise summary for a given month.

## Endpoint

```http
GET /api/v1/expenses/summary/{year}/{month}
```

## Example

```http
GET /api/v1/expenses/summary/2026/8
```

## Success Response

```json
{
    "timestamp": "...",
    "data": {
        "totalExpenses": 2149.99,
        "categoryWiseExpenses": {
            "Food": 1500.00,
            "Entertainment": 649.99
        }
    },
    "message": "Expense summary retrieved successfully for August 2026",
    "error": null
}
```

## Error Response

```json
{
    "timestamp": "...",
    "data": null,
    "message": "Resource not found",
    "error": {
        "status": 404,
        "message": "No expenses found for 8/2026",
        "subErrors": []
    }
}
```

---

# 7️⃣ Delete Expense

Deletes an existing expense.

## Endpoint

```http
DELETE /api/v1/expenses/{expenseId}
```

## Example

```http
DELETE /api/v1/expenses/1
```

## Success Response

```json
{
    "timestamp": "...",
    "data": null,
    "message": "Resource deleted successfully",
    "error": null
}
```

## Error Response

```json
{
    "timestamp": "...",
    "data": null,
    "message": "Resource not found",
    "error": {
        "status": 404,
        "message": "Expense not found with id: 1",
        "subErrors": []
    }
}
```

---

# 📨 Example cURL Commands

### Add Expense

```bash
curl -X POST http://localhost:8080/api/v1/expenses \
-H "Content-Type: application/json" \
-d '{
"title":"Pizza",
"amount":499.99,
"category":"Food",
"date":"2026-08-01"
}'
```

### Get All Expenses

```bash
curl http://localhost:8080/api/v1/expenses
```

### Search Expenses

```bash
curl http://localhost:8080/api/v1/expenses/search/Pizza
```

### Monthly Summary

```bash
curl http://localhost:8080/api/v1/expenses/summary/2026/8
```

### Delete Expense

```bash
curl -X DELETE http://localhost:8080/api/v1/expenses/1
```

---

# 📌 API Design Notes

- RESTful endpoint naming convention
- Layered architecture (Controller → Service → Service Implementation)
- Global exception handling for consistent error responses
- Standardized API response wrapper
- Input validation using Jakarta Bean Validation
- In-memory storage using `HashMap`
- Thread-safe unique ID generation using `AtomicLong`
- Dockerized for easy deployment
- Comprehensive unit testing for service and controller layers

---

# 🏆 Optional Bonus Features Implemented

This submission includes the following optional enhancements:

- 🔍 Search Expenses
- 📅 Monthly Expense Summary
- 🐳 Docker Support

These features were implemented in addition to the core assessment requirements.

---

# 🏛 Design Decisions

This project was intentionally designed using a layered architecture to promote separation of concerns, maintainability, and future extensibility.

## Controller Layer

The controller layer is responsible only for:

- Exposing REST endpoints
- Accepting HTTP requests
- Performing request validation
- Delegating business logic to the service layer
- Returning HTTP responses

No business logic is implemented inside controllers.

---

## Service Layer

The service layer contains all business logic of the application.

Responsibilities include:

- Expense creation
- Expense retrieval
- Expense deletion
- Expense filtering
- Expense searching
- Expense summary generation
- Monthly summary calculation
- Data validation beyond request validation

The project follows the Interface + Implementation pattern.

```
ExpenseService
        │
        ▼
ExpenseServiceImpl
```

This makes future enhancements and testing significantly easier.

---

## DTO-Based Communication

The API never exposes internal model objects directly.

Instead it uses dedicated DTOs for:

- Request payloads
- Response payloads
- Summary responses

Benefits include:

- Better encapsulation
- Safer API contracts
- Easier future modifications
- Cleaner serialization

---

## Global Exception Handling

A centralized exception handling mechanism is implemented using:

```
@RestControllerAdvice
```

This provides:

- Consistent error responses
- Proper HTTP status codes
- Cleaner controllers
- Better client experience

---

## Global Response Wrapper

All successful API responses follow a standardized format using a global response handler.

Example:

```json
{
    "timestamp": "...",
    "data": {},
    "message": "...",
    "error": null
}
```

This ensures consistency across every endpoint.

---

## Why HashMap?

The assessment explicitly requested an in-memory implementation; therefore, a `HashMap` was chosen to keep the project lightweight while allowing an easy migration to a persistent database in the future.

---

# 📊 Testing Strategy

The project contains automated unit tests covering both the business logic and the REST layer.

## Service Layer Tests

Business logic is tested using:

- JUnit 5
- Mockito

Covered scenarios include:

- Expense Creation
- Expense Retrieval
- Category Filtering
- Expense Searching
- Expense Summary
- Monthly Summary
- Expense Deletion
- Exception Scenarios

---

## Controller Layer Tests

REST endpoints are tested using:

- MockMvc
- Spring Boot Test

Covered scenarios include:

- Endpoint mapping
- HTTP Status Codes
- Request validation
- JSON responses
- Service interaction

---

# 🧠 Assumptions

The following assumptions were made while implementing this project:

- The application uses an in-memory `HashMap` instead of a database.
- All stored data is lost once the application stops.
- Expense IDs are generated using `AtomicLong`.
- Category names are treated as case-insensitive during searching.
- No authentication or authorization is required.
- Only a single application instance is assumed.
- Pagination is intentionally omitted due to the in-memory nature of the assessment.

---

# ⚠ Limitations

Current limitations of the project include:

- No persistent database
- No authentication
- No authorization
- No pagination
- No sorting
- No Swagger/OpenAPI documentation
- No audit logging
- No user management

These limitations were intentionally kept outside the scope of the assessment.

---

# 🚀 Future Improvements

The project has been designed in a way that allows several production-ready enhancements with minimal architectural changes.

Potential future improvements include:

- PostgreSQL/MySQL integration
- Spring Data JPA
- JWT Authentication
- Role-Based Authorization
- Swagger/OpenAPI Documentation
- Pagination & Sorting
- Expense Update API
- User Accounts
- Expense Analytics Dashboard
- Redis Caching
- Docker Multi-stage Build
- CI/CD Pipeline using GitHub Actions
- Kubernetes Deployment
- Cloud Deployment (AWS/GCP/Azure)

---

# 📦 Build Commands

Build project

```bash
mvn clean install
```

Run tests

```bash
mvn test
```

Run application

```bash
mvn spring-boot:run
```

Package application

```bash
mvn clean package
```

Verify build (commonly used in CI pipelines)

```bash
mvn clean verify
```

---

# 🤝 Contributing

Contributions, improvements, and suggestions are always welcome.

If you would like to contribute:

1. Fork the repository.
2. Create a feature branch.
3. Commit your changes.
4. Open a Pull Request.

---

# 👨‍💻 Author

**Soumik Maity**

Backend Developer | Java | Spring Boot | REST APIs | Software Engineering Enthusiast

GitHub:
```
https://github.com/Soumik2426
```

LinkedIn:
```
https://www.linkedin.com/in/soumik58567/
```

---

#  Acknowledgements

This project was developed as part of a backend engineering assessment to demonstrate:

- REST API Design
- Clean Code Principles
- Layered Architecture
- Exception Handling
- Validation
- Unit Testing
- Dockerization
- Documentation

---

<div align="center">

Made with ❤️ by **Soumik Maity**

</div>
