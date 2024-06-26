# Smart Parking Application

## Overview

This repository contains the backend implementation of the Smart Parking Application. It provides RESTful APIs to manage administrative users.

## Requirements

To run this application locally, ensure you have the following installed:

- Java Development Kit (JDK) 21 or higher(wherever that is)
- Apache Maven
- PostgreSQL database
- Redis for caching
- Kafka for Messaging

## Setup Instructions

1. **Clone the repository:**

   ```bash
   git clone https://github.com/BabalolaBrainiac/SmartParking.git
   cd SmartParking
   ```

2. **Set up the Database:**

    - Create a PostgreSQL database named `smart_parking_db`.
    - Configure the database connection in `src/main/resources/application.properties`.

      ```properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/smart_parking_db
      spring.datasource.username=your_username
      spring.datasource.password=your_password
      ```

3. **Build the Application:**

   ```bash
   mvn clean package
   ```

4. **Run the Application:**

   ```bash
   java -jar target/smart-parking-application.jar
   ```

   The application will start running on `http://localhost:8080`.

## API Documentation

### Admin Users API

The Admin Users API allows you to manage administrative users.

#### Create a new Admin User

- **URL:** `http://localhost:8080/api/v1/admin-users`
- **Method:** `POST`
- **Request Body:**

  ```json
  {
    "firstName": "John",
    "lastName": "Doe",
    "email": "johndoe@example.com",
    "phoneNumber": "+1234567890",
    "userType": "ADMIN"
  }
  ```

- **Responses:**
    - `201 Created`: Admin user created successfully.
    - `400 Bad Request`: Invalid input.
    - `409 Conflict`: Admin user with the same email or phone number already exists.
    - `500 Internal Server Error`: Unexpected error occurred.

#### Get an Admin User by ID

- **URL:** `http://localhost:8080/api/v1/admin-users/{id}`
- **Method:** `GET`
- **Path Variable:** `id` - ID of the admin user.
- **Responses:**
    - `200 OK`: Admin user found.
    - `404 Not Found`: Admin user not found.

#### Get all Admin Users

- **URL:** `http://localhost:8080/api/v1/admin-users`
- **Method:** `GET`
- **Responses:**
    - `200 OK`: List of all admin users.

#### Update an Admin User

- **URL:** `http://localhost:8080/api/v1/admin-users/{id}`
- **Method:** `PUT`
- **Path Variable:** `id` - ID of the admin user.
- **Request Body:**

  ```json
  {
    "id": 1,
    "firstName": "Jane",
    "lastName": "Smith",
    "email": "janesmith@example.com",
    "phoneNumber": "+9876543210",
    "userType": "ADMIN"
  }
  ```

- **Responses:**
    - `200 OK`: Admin user updated successfully.
    - `404 Not Found`: Admin user not found.
    - `400 Bad Request`: Invalid input.

#### Delete an Admin User

- **URL:** `http://localhost:8080/api/v1/admin-users/{id}`
- **Method:** `DELETE`
- **Path Variable:** `id` - ID of the admin user.
- **Responses:**
    - `204 No Content`: Admin user deleted successfully.
    - `404 Not Found`: Admin user not found.

## Technologies Used

- Java
- Spring Boot
- PostgreSQL
- Maven

## Contributors

- [Babalola Opeyemi Daniel](https://github.com/BabalolaBrainiac)

---

Replace `{id}` with the actual ID of the admin user you want to interact with. Adjust the configuration details like database username, password, and other environment-specific configurations as needed.