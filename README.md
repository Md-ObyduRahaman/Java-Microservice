# School Management System Microservices

This project is a School Management System that is divided into multiple microservices. Each microservice handles a specific part of the system and communicates with other services to provide a complete solution.

## Microservices

### 1. Service registry
- **Port**: 8761
- **Description**: Service Registry is a centralized database that keeps track of the various services in a microservices architecture. It maintains a list of service instances, their locations, and their statuses, allowing microservices to discover and communicate with each other dynamically.
### 2. Config-Server
- **Port**: 8000
- **Description**: A Config Server in a microservice architecture is a centralized server that manages the external configuration for distributed systems. It provides a way to decouple configuration settings from the application code, enabling better flexibility and easier management, especially when dealing with multiple microservices.
### 3. API Gateway
- **Port**: 8080
- **Description**: This API Gateway is a crucial component in microservice architectures that acts as a reverse proxy, managing requests from clients and routing them to the appropriate microservices. It serves as an entry point to your system and provides several key functionalities to simplify and secure client-to-microservice interactions.
### 4. Login System
- **Port**: 8081
- **Description**: This microservice handles user authentication and authorization. It manages login, logout, and token-based authentication using JWT.

### 5. Students Management System
- **Port**: 8082
- **Description**: This microservice manages Students information including registration, updates, and retrieval of Students records.

### 6. Parents Management System
- **Port**: 8083
- **Description**: This microservice manages parents information including registration, updates, and retrieval of parents records.

---

