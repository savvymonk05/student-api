# Student API

A RESTful API built with Spring Boot for managing student records.

## Tech Stack

- Java 21
- Spring Boot 4.x
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

## Features

- Full CRUD operations
- DTO pattern (Request/Response separation)
- Global Exception Handling
- Input Validation
- Clean layered architecture (Controller → Service → Repository)

## Project Structure
src/main/java/com/himanshu/student_api/
├── controller/        → REST endpoints
├── service/           → Business logic
├── repository/        → Database operations
├── model/             → JPA Entity
├── dto/               → Request and Response DTOs
└── exception/         → Custom exceptions and global handler

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /students | Get all students |
| GET | /students/{id} | Get student by ID |
| POST | /students | Create new student |
| PUT | /students/{id} | Update student |
| DELETE | /students/{id} | Delete student |

## Request Body (POST / PUT)
```json
{
    "name": "Himanshu",
    "email": "himanshu@gmail.com",
    "age": 22
}
```

## Validation Rules

- Name: cannot be empty
- Email: must be valid format
- Age: must be greater than 0

## Error Responses

| Status | Scenario |
|--------|----------|
| 404 | Student not found |
| 400 | Validation failed |

## Setup

1. Clone the repository
2. Create a PostgreSQL database named `studentdb`
3. Update `application.properties` with your DB credentials
4. Run the application
5. Test endpoints via Postman on `http://localhost:8080`
