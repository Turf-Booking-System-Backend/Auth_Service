# 🔐 Turf Booking Platform — Auth Service

Authentication and authorization microservice for the Turf Booking Platform.

## 📌 Overview

The **Auth Service** is responsible for user identity, authentication, password security, JWT generation, and role-based authorization.

It acts as the security foundation of the platform.

### Roles

* `USER` — Book turfs and manage personal bookings
* `VENDOR` — Manage vendor profile and own turfs
* `ADMIN` — Internal platform administration

---

## 🏗️ Architecture

```text
Client
  │
  ├── Register
  │
  └── Login
        │
        ▼
   Auth Service
        │
        ├── Validate credentials
        ├── BCrypt password verification
        ├── Generate JWT
        └── Include role in JWT
```

---

## ✨ Features

* User registration
* User login
* BCrypt password encryption
* JWT generation
* JWT validation
* JWT filter
* Stateless authentication
* Role-based authorization
* `@PreAuthorize` method security
* USER / VENDOR / ADMIN roles
* Global exception handling
* Validation
* Custom unauthorized / access-denied responses
* Public health endpoint

---

## 🛠️ Technology Stack

| Technology         | Purpose                        |
| ------------------ | ------------------------------ |
| Java 17            | Backend language               |
| Spring Boot        | Application framework          |
| Spring Security    | Authentication & authorization |
| JWT                | Stateless authentication       |
| Spring Data JPA    | Database access                |
| Hibernate          | ORM                            |
| MySQL              | Database                       |
| Maven              | Dependency management          |
| Lombok             | Boilerplate reduction          |
| Jakarta Validation | Request validation             |
| Docker             | MySQL environment              |
| Postman            | API testing                    |
| Git / Bitbucket    | Source control                 |

---

## 📁 Project Structure

```text
src/main/java
└── com.faizan.turfbooking.authservice
    ├── config
    ├── controller
    ├── constant
    ├── dto
    ├── entity
    ├── exception
    ├── jwt
    ├── repository
    ├── security
    └── service
```

---

## 🔑 Authentication Flow

```text
POST /login
     │
     ▼
Validate email/password
     │
     ▼
Find user
     │
     ▼
Verify BCrypt password
     │
     ▼
Generate JWT
     │
     ▼
Return token
```

Protected requests:

```text
Authorization: Bearer <JWT>
```

The JWT contains the authenticated user's role.

Spring Security converts the role into authorities such as:

```text
ROLE_USER
ROLE_VENDOR
ROLE_ADMIN
```

---

## 🔐 Role-Based Authorization

Example:

```java
@PreAuthorize("hasRole('ADMIN')")
```

Only ADMIN users can access the protected method.

Examples:

```text
USER    → USER APIs
VENDOR  → VENDOR APIs
ADMIN   → ADMIN APIs
```

---

## 🌐 Main APIs

### Health

```http
GET /health
```

Public endpoint.

### Registration

```http
POST /v1/auth/users
```

Example:

```json
{
  "name": "Jane Smith",
  "email": "jane@example.com",
  "password": "password123"
}
```

### Login

```http
POST /v1/auth/login
```

Returns JWT.

---

## 🗄️ Database

Database:

```text
auth_service_db
```

Main table:

```text
users
```

Important fields include:

```text
id
name
email
password
role
```

Passwords are **never stored as plain text**.

---

## 🐳 MySQL Docker Setup

The project uses MySQL through Docker.

Typical port mapping:

```text
3307:3306
```

Meaning:

```text
3307 → Host machine
3306 → Docker MySQL container
```

Spring Boot connects through:

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/auth_service_db
```

---

## ⚙️ Configuration

Create your own `application.properties`.

Do **NOT** commit real passwords, JWT secrets, or production credentials.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3307/auth_service_db
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD

jwt.secret=YOUR_JWT_SECRET
jwt.expiration=YOUR_EXPIRATION_TIME
```

Replace the placeholders with your local credentials.

---

## 🚀 Running Locally

### 1. Start MySQL container

```bash
docker start <mysql-container>
```

### 2. Create database

```sql
CREATE DATABASE auth_service_db;
```

### 3. Configure `application.properties`

Replace:

```text
YOUR_MYSQL_USERNAME
YOUR_MYSQL_PASSWORD
YOUR_JWT_SECRET
```

### 4. Start application

```bash
mvn spring-boot:run
```

---

## 🧪 Testing

APIs were tested using Postman.

Security scenarios include:

* No token → `401 Unauthorized`
* Invalid token → `401 Unauthorized`
* Valid USER token → USER access
* Valid VENDOR token → VENDOR access
* Valid ADMIN token → ADMIN access
* Wrong role → `403 Forbidden`

---

## 🔒 Security Notes

The project uses:

* Stateless JWT authentication
* BCrypt password hashing
* Method-level authorization
* Modern `SecurityFilterChain`
* No server-side session authentication

Spring Boot's generated default security password is disabled because the service uses custom JWT authentication.

## 📈 Current Status

### Completed

* [x] Project setup
* [x] Database setup
* [x] User entity
* [x] Registration
* [x] Validation
* [x] BCrypt encryption
* [x] Login
* [x] JWT generation
* [x] JWT filter
* [x] Spring Security configuration
* [x] Role-based authorization
* [x] USER / VENDOR / ADMIN access control
* [x] Exception handling
* [x] Security testing

### Status

**V1 + V2 — Completed ✅**

---

## 🎯 Future Integration

The Auth Service will later work with the other microservices through the platform's security architecture.

The project is intentionally developed incrementally so that each service can be understood, tested, and integrated independently.
