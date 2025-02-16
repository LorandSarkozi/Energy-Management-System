# Energy Management System

The **Energy Management System** is a microservices-based application designed to monitor and manage energy consumption across devices. The system consists of a backend built with Spring Boot, a frontend developed in Angular, and uses Docker for containerization and MySQL as the database. The architecture follows the **microservices design pattern**, which brings several advantages for scaling, managing, and updating individual components of the system.

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Technologies Used](#technologies-used)

## Features

- **Role-Based Login**: Separate login views for customers and admins.
- **Customers**: Can view their connected devices and monitor energy usage.
- **Admins**: Have full access to view, create, modify, and delete users and devices.
- **Device Monitoring**: Tracks hourly energy consumption for each device.
- **JWT Authentication**: Secure authentication with JSON Web Tokens.
- **Microservices Architecture**: Independent services for user, device management, chat, and measurements.
- **Angular Frontend**: Responsive and user-friendly interface.
- **Chat Service**: Real-time communication between customers and admins via WebSockets.
- **Measurements Service**: Simulates device data and processes it to ensure energy consumption is within acceptable limits. A graph visualization of hourly energy consumption is provided.

## Architecture

The project follows a **microservices architecture**, where each service is responsible for a specific set of tasks. Microservices allow for easier scaling and maintenance, as individual components can be updated or replaced without affecting the entire system.

### Pros of Microservices Architecture:
- **Scalability**: Each service can be scaled independently based on its requirements. For example, if the device service needs more resources, it can be scaled without affecting other services.
- **Fault Isolation**: If one microservice fails, it does not affect the others, which improves system reliability.
- **Technology Flexibility**: Different microservices can be developed using different technologies. For example, you could use Java for one microservice and Python for another.
- **Faster Development**: Teams can work on different services independently, reducing development time and enabling continuous delivery.

### Components:

1. **User Service (spring-demo)**: Manages user accounts, authentication, and roles (admin/customer).
2. **Device Service (spring-demo-devices)**: Handles device information and tracks energy consumption.
3. **Chat Service (spring-demo-chat)**: A real-time communication service that allows customers to chat with admins and vice versa, using WebSockets for real-time messaging.
4. **Measurements Service (spring-demo-measurements)**: Responsible for processing energy consumption data and displaying graphs. It includes a device-simulator that retrieves data from a CSV file, processes it, and checks if the hourly energy consumption exceeds the allowed limit. If the consumption exceeds the threshold, an error message is displayed.

Each service is containerized with Docker, allowing easy deployment and scalability. The services communicate with each other through REST APIs, and data is stored in separate MySQL databases.

## Dockerization

The project is fully containerized using **Docker**. This enables easy deployment and scalability of the microservices. The following components are dockerized:

- **Backend Services**: Each of the backend microservices (User, Device, Chat, and Measurements) is containerized using Docker. They each have a dedicated Dockerfile that defines the build process.
- **Frontend**: The Angular frontend is also containerized, allowing it to run independently in a Docker container.
- **MySQL Database**: A MySQL database is used to store service-related data and is containerized using Docker.
- **Docker Compose**: The entire system, including all microservices and the MySQL database, is orchestrated with Docker Compose. This ensures that the services can communicate with each other and be deployed together in a unified way.

