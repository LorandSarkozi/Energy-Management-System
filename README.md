# Energy Management System

The **Energy Management System** is a microservices-based application designed to monitor and manage energy consumption across devices. The system consists of a backend built with Spring Boot, a frontend developed in Angular, and uses Docker for containerization and MySQL as the database.

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

- **Role-Based Login**: Separate login views for customers and admins.
  - **Customers**: Can view their connected devices and monitor energy usage.
  - **Admins**: Have full access to view, create, modify, and delete users and devices.
- **Device Monitoring**: Tracks hourly energy consumption for each device.
- **JWT Authentication**: Secure authentication with JSON Web Tokens.
- **Microservices Architecture**: Two independent services for user and device management.
- **Angular Frontend**: Responsive and user-friendly interface.

## Architecture

The project follows a microservices architecture with the following components:

1. **User Service (spring-demo)**: Manages user accounts, authentication, and roles (admin/customer).
2. **Device Service (spring-demo-devices)**: Handles device information and tracks energy consumption.

Each service is containerized with Docker, allowing easy deployment and scalability. The two services communicate through REST APIs, and data is stored in separate MySQL databases.

## Technologies Used

- **Backend**: Spring Boot, Spring Security, JWT
- **Frontend**: Angular
- **Database**: MySQL
- **Containerization**: Docker
- **Others**: Maven for dependency management

## Setup and Installation

### Prerequisites

- **Java 17** or higher
- **Node.js** and **npm** (for Angular frontend)
- **Docker** and **Docker Compose**
- **MySQL**

### Backend Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/energy-management-system.git
   cd energy-management-system
