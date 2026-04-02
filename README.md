# Time Tracker API

A backend API for managing employee time tracking, attendance, shifts, time-off requests, notifications, and user access control.

This project is built with **Java**, **Spring Boot**, **Spring Security**, **Spring Data JPA**, **JWT authentication**, and **Microsoft SQL Server**. It exposes RESTful endpoints and includes Swagger/OpenAPI documentation for testing and exploration.

## Overview

The Time Tracker API is designed as a SaaS-style workforce management backend that helps organizations:

- register and manage users
- authenticate users with JWT
- track employee clock-in and clock-out activity
- manage shifts and publishing workflow
- handle time-off requests and approvals
- manage notifications
- organize users under organizations and roles

## Core Features

### Authentication
- user registration
- login with JWT
- refresh token flow
- forgot password / reset password
- change password
- email verification and resend verification
- logout endpoint

### User and Role Management
- create and manage users
- assign and remove roles
- fetch user roles
- organize users by organization

### Attendance Tracking
- clock in
- clock out
- fetch attendance history by user

### Shift Management
- create shifts
- publish shifts
- fetch user shifts
- delete shifts

### Time-Off Management
- submit time-off requests
- approve or reject requests
- fetch user requests
- fetch pending requests

### Notifications
- fetch all notifications for a user
- fetch unread notifications
- count unread notifications
- mark notifications as read

### Organization Management
- create organization
- list organizations
- get organization by ID
- update organization
- delete organization

## Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Security**
- **Spring Data JPA**
- **Hibernate**
- **Microsoft SQL Server**
- **JWT (jjwt)**
- **Swagger / OpenAPI**
- **Lombok**
- **Maven**

## Project Structure

```text
src/main/java/com/kingalex/timetracker
├── config
├── controller
├── domain/entity
├── dto
├── repository
├── security
├── service
└── TimetrackerApplication.java
```

## Security Model

The API uses **JWT-based stateless authentication**.

### Public Endpoints
- `/api/auth/**`
- `/swagger-ui/**`
- `/swagger-ui.html`
- `/v3/api-docs/**`

### Role-Based Access
- **ADMIN**
  - `/api/organizations/**`
- **ADMIN / MANAGER**
  - `/api/shifts/**`
  - `/api/reports/**`
  - `/api/time-off/pending`
- **Authenticated users**
  - all remaining protected endpoints

## Database

The application uses **Microsoft SQL Server** with JPA/Hibernate.

### Main entities
- Organization
- User
- Role
- UserRole
- AttendanceRecord
- Shift
- TimeOffRequest
- Notification
- Project
- ProjectMember
- ProjectTimeLog
- AuditLog

## Environment Variables

Use environment variables for sensitive values instead of committing secrets into source control.

Example configuration:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
app.jwt.secret=${JWT_SECRET}
app.jwt.expiration=${JWT_EXPIRATION:86400000}
```

Example values for local development:

```text
DB_URL=jdbc:sqlserver://localhost:1433;databaseName=timetracker;encrypt=true;trustServerCertificate=true
DB_USERNAME=sa
DB_PASSWORD=your_password
JWT_SECRET=your_very_long_secret_key
JWT_EXPIRATION=86400000
```

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/davefurn/timetracker.git
cd timetracker
```

### 2. Configure environment variables

Set the following environment variables in your IDE or terminal:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`
- `JWT_SECRET`
- `JWT_EXPIRATION`

### 3. Start SQL Server

Make sure your SQL Server instance is running and the target database exists.

Example database name:

- `timetracker`

### 4. Build and run the application

Using Maven wrapper:

```bash
./mvnw spring-boot:run
```

Or on Windows:

```bash
mvnw.cmd spring-boot:run
```

### 5. Access the application

- API base URL: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI docs: `http://localhost:8080/v3/api-docs`

## Example API Endpoints

### Auth
- `POST /api/auth/register`
- `POST /api/auth/login`
- `POST /api/auth/forgot-password`
- `POST /api/auth/reset-password`
- `POST /api/auth/change-password`
- `GET /api/auth/verify-email`
- `POST /api/auth/resend-verification`
- `POST /api/auth/refresh-token`
- `POST /api/auth/logout`

### Users
- `POST /api/users`
- `GET /api/users`
- `GET /api/users/{id}`
- `GET /api/users/organization/{organizationId}`
- `PUT /api/users/{id}`
- `DELETE /api/users/{id}`
- `POST /api/users/{userId}/roles/{roleName}`
- `DELETE /api/users/{userId}/roles/{roleName}`
- `GET /api/users/{userId}/roles`

### Organizations
- `POST /api/organizations`
- `GET /api/organizations`
- `GET /api/organizations/{id}`
- `PUT /api/organizations/{id}`
- `DELETE /api/organizations/{id}`

### Attendance
- `POST /api/attendance/clock-in`
- `POST /api/attendance/clock-out`
- `GET /api/attendance/user/{userId}`

### Shifts
- `POST /api/shifts`
- `PUT /api/shifts/{id}/publish`
- `GET /api/shifts/user/{userId}`
- `DELETE /api/shifts/{id}`

### Time Off
- `POST /api/time-off`
- `PUT /api/time-off/{id}/approve`
- `PUT /api/time-off/{id}/reject`
- `GET /api/time-off/user/{userId}`
- `GET /api/time-off/pending`

### Notifications
- `GET /api/notifications/user/{userId}`
- `GET /api/notifications/user/{userId}/unread`
- `GET /api/notifications/user/{userId}/unread/count`
- `PUT /api/notifications/{id}/read`

## Development Notes

### Recommended local settings
For a cleaner REST API setup, consider these properties in development:

```properties
spring.jpa.open-in-view=false
```

If Hibernate auto-detects SQL Server correctly, you can remove an explicit dialect property.

### Git hygiene
Before pushing publicly:

- do not commit secrets
- ignore `target/`, `.idea/`, local config files, and OS-generated files
- use environment variables for sensitive settings

## Testing

Recommended next steps for tests:

- service layer unit tests with **JUnit 5** and **Mockito**
- controller tests with **MockMvc**
- integration tests for persistence and security flows

High-value starting points:

- `AttendanceService`
- `UserService`
- `AuthService`

## Suggested Improvements

To move this project closer to production standard:

- replace generic `RuntimeException` with custom exceptions
- add a global exception handler
- move secrets fully to environment variables
- switch from `ddl-auto=update` to **Flyway** or **Liquibase**
- add unit and integration tests
- add request/response examples to Swagger
- introduce audit logging for sensitive admin actions
- review authorization checks around organization boundaries
- clean up test dependencies in `pom.xml`

## Author

**Alex Ogubuike (King Alex)**

Swagger contact currently set to:
- **Okoh David**
- `davidokoh2000@gmail.com`

Update this section if you want the README and Swagger metadata to reflect the same owner details.

## License

This project is currently for learning and portfolio development. Add a license such as **MIT** if you plan to make it publicly reusable.
