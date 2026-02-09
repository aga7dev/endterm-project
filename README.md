# HotelBooking API

## A. Project Overview

HotelBooking API is a **simple student project** built with Spring Boot. The main goal of this project is to learn how to create a REST API, connect it to a database, and test it using Postman.

The application allows basic management of hotels and bookings. The project was created as part of a first-year course assignment.

---

## B. REST API Documentation

### Base URL

The application runs locally on:

```
http://localhost:8080/api
```

### Hotel API

The hotel API supports basic CRUD operations.

Available endpoints:

* GET `/hotels` – returns a list of all hotels
* GET `/hotels/{id}` – returns one hotel by its ID
* POST `/hotels` – creates a new hotel
* PUT `/hotels/{id}` – updates an existing hotel
* DELETE `/hotels/{id}` – deletes a hotel

Example request to create a hotel:

```json
{
  "name": "Hilton",
  "city": "Almaty",
  "stars": 5
}
```

Example response:

```json
{
  "id": 1,
  "name": "Hilton",
  "city": "Almaty",
  "stars": 5
}
```

### Booking API

The booking API allows creating and viewing bookings.

Available endpoints:

* GET `/bookings` – returns all bookings
* GET `/bookings/{id}` – returns a booking by ID
* POST `/bookings` – creates a new booking

All endpoints were tested using Postman. 

------|---------|------------|
| GET | /hotels | Get all hotels |
| GET | /hotels/{id} | Get hotel by ID |
| POST | /hotels | Create a hotel |
| PUT | /hotels/{id} | Update hotel |
| DELETE | /hotels/{id} | Delete hotel |

#### Example: Create Hotel (POST /hotels)

```json
{
  "name": "Hilton",
  "city": "Almaty",
  "stars": 5
}
```

#### Example Response

```json
{
  "id": 1,
  "name": "Hilton",
  "city": "Almaty",
  "stars": 5
}
```

### Booking Endpoints

| Method | Endpoint       | Description       |
| ------ | -------------- | ----------------- |
| GET    | /bookings      | Get all bookings  |
| GET    | /bookings/{id} | Get booking by ID |
| POST   | /bookings      | Create booking    |

### Postman Testing

All endpoints were tested using **Postman**.
---

## C. Design Patterns

### Singleton

Spring services and repositories work as single instances. This means only one object is created and reused.

### Factory

A simple factory is used to create booking objects in one place.

### Builder

Builder is used to create response objects step by step to make the code cleaner.

---

## D. Component Principles

The project follows a simple layered structure:

* Controller – handles HTTP requests
* Service – contains business logic
* Repository – works with the database

Each part has its own responsibility.

---

## E. SOLID & OOP Summary

Basic SOLID principles were followed:

* Classes have clear responsibilities
* Dependencies are injected using Spring

OOP concepts used in the project:

* Encapsulation
* Inheritance
* Polymorphism

---

## F. Database Schema

The project uses a PostgreSQL database with two main tables.

**Hotel** table contains:

* id (primary key)
* name (hotel name)
* city (hotel location)
* stars (hotel rating)

**Booking** table contains:

* id (primary key)
* start_date (booking start date)
* end_date (booking end date)
* hotel_id (reference to hotel)

------|------|
| id | BIGINT |
| name | VARCHAR |
| city | VARCHAR |
| stars | INT |

### Booking Table

| Field      | Type   |
| ---------- | ------ |
| id         | BIGINT |
| start_date | DATE   |
| end_date   | DATE   |
| hotel_id   | BIGINT |

---

## G. System Architecture Diagram

```
Client (Postman)
      ↓
Controller
      ↓
Service
      ↓
Repository
      ↓
PostgreSQL Database
```

---

## H. How to Run the Application

### Requirements

* Java 17
* Maven
* PostgreSQL

### Steps

1. Create database:

```sql
CREATE DATABASE hotel_booking_db;
```

2. Configure `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/hotel_booking_db
spring.datasource.username=postgres
spring.datasource.password=postgres
```

3. Run the application:

```bash
./mvnw spring-boot:run
```

4. Test endpoints using Postman

---

## I. Reflection

This project helped me understand how Spring Boot works, how REST APIs are created, and how to connect an application to a database. I also learned how to use Postman to test API endpoints and debug errors. The project improved my understanding of backend development basics.
