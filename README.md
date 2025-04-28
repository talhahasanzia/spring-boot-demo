
# Spring Boot Project (Kotlin)

## Overview
This is a Spring Boot application written in Kotlin, using Gradle Kotlin DSL for build configuration. The project integrates MySQL as the database driver and implements token-based authentication for secure API access.

## Features
- **Spring Boot Framework**: Simplifies application development with Kotlin support.
- **MySQL Database**: Used as the relational database.
- **Token-Based Authentication**: Ensures secure access to APIs using JWT (JSON Web Tokens).
- **Authentication and Authorization**: Includes user authentication and role-based access control.

## Project Structure
- **`src/main/kotlin`**: Contains the Kotlin source code.
    - `controller`: Handles API endpoints.
    - `service`: Contains business logic.
    - `repository`: Interfaces for database operations.
    - `model`: Defines data models/entities.
    - `config`: Includes security and application configurations.
    - `util`: Contains utility classes for JWT handling and filtering.
- **`src/main/resources`**:
    - `application.properties`: Configuration file for database and application properties.
- **`build.gradle.kts`**: Gradle Kotlin DSL build script.

## Prerequisites
- Java 17 or higher
- Kotlin 1.8+
- Gradle 7.6+
- MySQL Server
- Postman or any API testing tool (optional)

## Setup Instructions
1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd demo
   ```

2. **Configure MySQL**:
    - Create a database in MySQL.
    - Update the `application.properties` file located in `src/main/resources` with your MySQL credentials:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/<database_name>
      spring.datasource.username=<your_username>
      spring.datasource.password=<your_password>
      spring.jpa.hibernate.ddl-auto=update
      jwt.secret=your_jwt_secret_key
      jwt.expiration=3600000
      ```

3. **Build the Project**:
   ```bash
   ./gradlew build
   ```

4. **Run the Application**:
   ```bash
   ./gradlew bootRun
   ```

5. **Access the Application**:
    - The application will be available at `http://localhost:8080`.

## Authentication
- The application uses JWT for token-based authentication.
- After logging in, a token will be provided, which must be included in the `Authorization` header for subsequent API requests:
  ```http
  Authorization: Bearer <token>
  ```

## Token and Security Logic
The application implements token-based authentication and security using the following components:

1. **JWT Utility (`JwtUtil.kt`)**:
    - Located in `src/main/kotlin/com/example/demo/util/JwtUtil.kt`.
    - Handles token generation and extraction of claims (e.g., username and roles).
    - Example:
      ```kotlin
      fun generateToken(username: String, roles: String): String {
          // Generates a JWT token with username and roles as claims.
      }
      ```

2. **JWT Filter (`JwtFilter.kt`)**:
    - Located in `src/main/kotlin/com/example/demo/util/JwtFilter.kt`.
    - Intercepts incoming requests to validate the token and set the authentication context.
    - Example:
      ```kotlin
      override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
          // Extracts the token from the Authorization header and validates it.
      }
      ```

3. **Security Configuration (`SecurityConfig.kt`)**:
    - Located in `src/main/kotlin/com/example/demo/config/SecurityConfig.kt`.
    - Configures Spring Security to use the JWT filter and disables session-based authentication.
    - Example:
      ```kotlin
      http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
      ```

## API Documentation
- API endpoints and usage details can be tested using Postman or Swagger (if integrated).

## Technologies Used
- **Spring Boot**
- **Kotlin**
- **Gradle Kotlin DSL**
- **MySQL**
- **JWT (JSON Web Token)** for authentication

## License
This project is licensed under the MIT License.

