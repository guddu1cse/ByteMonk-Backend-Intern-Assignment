# **ByteMonk-Backend-Intern-Assignment**

**Incident Management System - Backend**

This project is a Spring Boot application designed to manage incidents within an organization. It provides APIs for reporting, updating, and querying incidents, as well as user authentication and registration using JWT (JSON Web Token).


**Table of Contents**

1. Technologies Used
2. Project Structure
3. Setup Instructions
4. API Documentation
   4.1. Authentication
   4.2. Incident Management
5. Error Handling
6. Swagger API Documentation


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
                                                |   |           └── Impl              # Bussiness Logic Implementation
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

   **Request Body:**
    
        {
          "userName": "string",
          "password": "string"
        }

**Response Body:**

      {
        "jwtToken": "string",
        "userName": "string",
        "status": "ACCEPTED",
        "errorMessage": ""
      }


2. Register
Register a new user.

URL: /register

Method: POST

**Request Body:**

      {
        "username": "string",
        "password": "string",
        "email": "string",
        "roles": ["string"]
      }


**Response Body:**

          "Sign Up Successfully !"
          
**Incident Management**
1. Report Incident
Create a new incident report.

URL: /incident/report

Method: POST

Request Body:

       {
        "title": "string",
        "description": "string",
        "severity": "string",
        "incidentDate": "string (DD/MM/YYYY)"
       }

**Response Body if HttpStatus.OK**

           {
             "message": "Incident Report Successfully created !!",
             "validationError": [],
             "success": true,
             "status": "OK"
          }

**Response Body if getting Validations Error**
       
       {
          "message": "Invalid Incident Details !!",
          "validationError": [
              {
                  "errorField": "title",
                  "errorMessage": "title must be unique"
              },
              {
                  "errorField": "incidentDate",
                  "errorMessage": "incidents cannot be created with a past date greater than 30 days or a future date."
              }
          ],
          "success": false,
          "status": "BAD_REQUEST"
      }

2. Update Incident
   
Update an existing incident by its ID.

URL: /incident/report/{id}

Method: PUT

Request Body:

      {
        "title": "string",
        "description": "string",
        "severity": "string",
        "incidentDate": "string (DD/MM/YYYY)"
      }

   Response Body:

      "Incident updated successfully."


3. Get All Incidents
   
Retrieve a list of all incidents.

URL: /incident/getAll

Method: GET
      Response:

      [
        {
          "id": "string",
          "title": "string",
          "description": "string",
          "severity": "string",
          "incidentDate": "string (DD/MM/YYYY)"
        },
        ...
      ]

4. Get Incidents by Severity
   
Retrieve a list of incidents based on severity.

URL: /incident/{severity}

Method: GET
      Response:

      [
        {
          "id": "string",
          "title": "string",
          "description": "string",
          "severity": "string",
          "incidentDate": "string (DD/MM/YYYY)"
        },
        ...
      ]

5. Get Incidents by Date Range
   
Retrieve incidents reported between two dates.

URL: /incident/searchByDate

Method: POST
     Request Body:
     
         {
           "from": "string (DD/MM/YYYY)",
           "to": "string (DD/MM/YYYY)"
         }

   Response Body:

      [
        {
          "id": "string",
          "title": "string",
          "description": "string",
          "severity": "string",
          "incidentDate": "string (DD/MM/YYYY)"
        },
        ...
      ]


6. Get Incident by ID
   
Retrieve a specific incident by its ID.

URL: /incident/search/{id}

Method: GET
    Response:

       {
        "id": "string",
        "title": "string",
        "description": "string",
        "severity": "string",
        "incidentDate": "string (DD/MM/YYYY)"
      }

   **Error Handling**
400 Bad Request: Invalid input data, such as missing required fields or invalid date format.

403 Forbidden: Authentication failed or unauthorized access.

404 Not Found: Requested resource, such as an incident by ID, not found.

