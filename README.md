# Backend - Task Tacker API

## 📌 Project Overview
This is the backend for a task management application, built with **Java, Spring Boot, and MySQL**. It provides a secure REST API for managing tasks, including user authentication and authorization.

## 🚀 Technologies Used
- **Java 21**
- **Spring Boot** (Backend framework)
- **Spring Data JPA** (Database access)
- **Spring Security** (Authentication & Authorization)
- **MySQL** (Database)
- **Lombok** (Boilerplate code reduction)
- **Maven** (Dependency management)

## 🛠️ Setup & Installation

### 1️⃣ Prerequisites
Make sure you have the following installed:
- Java 17 or later
- Maven
- MySQL Server

### 2️⃣ Clone the repository
```sh
git clone https://github.com/jose2806/Task_Tracker.git
cd Task_Tracker
```

### 3️⃣ Configure the database
Create a **MySQL database** and update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/task_manager
spring.datasource.username=root
spring.datasource.password=yourpassword
```

### 4️⃣ Build and Run the project
```sh
mvn clean install
mvn spring-boot:run
```

## 🔐 Authentication
The API uses **JWT (JSON Web Tokens)** for authentication. Users must log in to receive a token, which should be included in the `Authorization` header for protected endpoints.

## 📌 API Endpoints
### 🔹 Authentication
- `POST /login` - Authenticate a user and return a JWT token.
- `POST /register` - Create a new user.

### 🔹 Task Management (Requires Authentication)
- `GET /tasks` - List all tasks
- `POST /tasks` - Create a new task
- `PUT /tasks/{id}` - Update a task

  ## 📌 Autor

👤 **Jose Rodriguez Cruz**  
💻 [GitHub](https://github.com/jose2806)  
---
