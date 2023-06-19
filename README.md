# Online Customer Service Center

## Description

This project is an online customer service center built with Spring Boot. It provides a platform for customers to submit requests, complaints, and inquiries which can be resolved by our dedicated support team. It uses JPA for data persistence, MySQL for the database, and includes validation and web functionalities. The project also employs Lombok to minimize boilerplate code.

## Team Members

- Member 1: [AmanChaudhary214 (TeamLead)](https://github.com/AmanChaudhary214)
- Member 2: [Suraj Kumar Sharma](https://github.com/geniusuraj)
- Member 3: [Lalith Tonangi](https://github.com/Lallu-123)
- Member 4: [Deepanshu Deep](https://github.com/Deepanshu-Deep)

## Tech Stack

- Spring Boot
- Spring Data JPA
- Spring Boot Validation
- Spring Boot Web
- Spring Boot DevTools
- MySQL Database
- Lombok

## Prerequisites

- Java 8 or higher
- Maven
- MySQL Server

## Installation

```bash
# To run this project locally:

# Clone the repository and navigate to the directory
git clone https://github.com/AmanChaudhary214/decisive-iron-5903.git
cd decisive-iron-5903

# Configure your MySQL credentials in application.properties
# located in src/main/resources directory
# Replace with your actual MySQL credentials
spring.datasource.url=jdbc:mysql://localhost:3306/your-database-name
spring.datasource.username=your-username
spring.datasource.password=your-password

# Use Maven to build the project
mvn clean install

# After successful build, navigate to target directory and run the jar file
java -jar target/decisive-iron-5903-0.0.1-SNAPSHOT.jar

# Your application should be up and running at http://localhost:8080.
```
# Usage

```bash
# After the application is running, you can interact with it using a tool like Postman to make HTTP requests.

# To get all customer requests
GET http://localhost:8080/customerRequests

# To submit a new customer request
POST http://localhost:8080/customerRequest
Content-Type: application/json
Request body:
{
  "name": "Your Name",
  "email": "Your Email",
  "description": "Your Request Description"
}

# To update a customer request
PUT http://localhost:8080/customerRequest/{id}
Content-Type: application/json
Request body:
{
  "name": "New Name",
  "email": "New Email",
  "description": "Updated Request Description"
}

# Replace {id} with the ID of the customer request you want to update
```
## Entity-Relationship Diagram

Here is the ER Diagram for our database:

![ER Diagram](https://github.com/AmanChaudhary214/decisive-iron-5903/blob/main/er.png)

