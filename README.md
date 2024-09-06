﻿# ByteMonk-Backend-Intern-Assignment

**Incident Management System - Backend**

This project is a Spring Boot application designed to manage incidents within an organization. It provides APIs for reporting, updating, and querying incidents, as well as user authentication and registration using JWT (JSON Web Token).


**Table of Contents**

-> Technologies Used
-> Project Structure
-> Setup Instructions
-> API Documentation
   ->Authentication
   ->Incident Management
-> Error Handling
->Swagger API Documentation


**Technologies Used**
        Java 21
        Spring Boot 3
        Spring Security for authentication and JWT
        Hibernate / JPA for ORM
        MySQL as the database
        Maven for dependency management
        Swagger API Documentation

                                                **Project Structure**

                                                src
                                                ├── main
                                                │   ├── java
                                                │   │   └── com.backend.backendApp
                                                |   |       ├── Config                # Security Configuration
                                                │   │       ├── Controller            # REST Controllers
                                                │   │       ├── Entity                # Entities for database
                                                │   │       ├── Repo                  # Repositories for CRUD operations
                                                │   │       ├── Service               # Business logic layer
                                                |   |           ├── Impl              # Bussiness Logic Implementation
                                                │   │       ├── JwtUtils              # JWT Token utilities
                                                |   |       ├── JwtFilter             # JWT Token Filter Chain
                                                |   |       ├── Response              # Responses Classes
                                                |   |       ├── ExceptionHandler      # Responses Classes
                                                │   ├── resources
                                                │   │   ├── application.properties  # Configuration for database and security
                                                │   └── test
                                                │       └── java

    **Prerequisites**
        Java 11+
        Maven
        MySQL


        **API Documentation**
Authentication
1. Login
    Authenticate a user and generate a JWT token.

URL: /auth/login
Method: POST
    Request Body: JSON
        {
          "userName": "string",
          "password": "string"
        }

        **Response Body if HttpStatus.OK**
            {
              "jwtToken": "string",
              "userName": "string",
              "status": "ACCEPTED",
              "errorMessage": ""
          }

       **Response Body if getting Validations Error**
        {
          "message": "Invalid Incident Details !!",
          "validationError": [
                                {
                                  "errorField": "title",
                                  "errorMessage": "title must be 10 or more characters"
                                },
                                {
                                  "errorField": "incidentDate",
                                  "errorMessage": "incidents cannot be created with a past date greater than 30 days or a future date."
                                }
                            ],
          "success": false,
          "status": "BAD_REQUEST"
        }


