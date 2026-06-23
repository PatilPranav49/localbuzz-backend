# LocalBuzz 2.0 Backend

## Overview

LocalBuzz is a community discovery platform that helps users discover nearby businesses, offers, events, products, and announcements in their locality.

Business owners can register businesses, publish updates, and promote local engagement, while users can explore businesses through feeds, maps, search, and business profiles.

---

## Features

### Authentication & Authorization

* User Registration
* User Login
* JWT Authentication
* BCrypt Password Encryption
* Role-Based Access Control

Supported Roles:

* USER
* OWNER
* ADMIN

---

### Business Management

Business owners can:

* Register Businesses
* View Their Businesses
* Manage Business Information

Supported Categories:

* CAFE
* BAKERY
* RESTAURANT
* GYM
* SALON
* MEDICAL
* JEWELLERY
* OTHER

---

### Admin Approval Workflow

All businesses must be approved before becoming publicly visible.

Workflow:

OWNER Creates Business

↓

PENDING

↓

ADMIN Reviews

↓

APPROVED / REJECTED

---

### Business Updates

Owners can publish:

* OFFER
* EVENT
* PRODUCT
* SERVICE
* ANNOUNCEMENT

Updates are visible only for approved businesses.

---

### Discovery Feed

Users can browse the latest updates from approved businesses.

Endpoint:

GET /feed

---

### Map Discovery

Users can discover nearby businesses using location-based search.

Endpoint:

GET /feed/nearby?lat={lat}&lng={lng}&radius={radius}

Features:

* Radius Filtering
* Distance Calculation
* Latest Business Update
* Map-Friendly Response

---

### Feed Filtering

Filter feed results by:

* Business Category
* Update Type

Examples:

GET /feed?category=CAFE

GET /feed?type=OFFER

GET /feed?category=CAFE&type=OFFER

---

### Business Search

Search approved businesses by:

* Business Name
* Business Category

Example:

GET /businesses/search?keyword=cafe

---

### Public Business Profile

View complete business details and recent updates.

Example:

GET /businesses/{id}

---

## Tech Stack

* Java 24
* Spring Boot
* Spring Security
* JWT Authentication
* PostgreSQL
* Spring Data JPA
* Hibernate
* Maven

---

## Architecture

Package-by-Feature Architecture

DTO → Service → Repository → Entity

Modules:

* auth
* user
* business
* admin
* update
* feed
* security

---

## Core API Endpoints

### Authentication

POST /users/register

POST /users/login

---

### Business

POST /businesses

GET /businesses/my

GET /businesses/search

GET /businesses/{id}

---

### Admin

POST /admin/businesses/{id}/approve

POST /admin/businesses/{id}/reject

---

### Updates

POST /updates

GET /businesses/{id}/updates

---

### Feed

GET /feed

GET /feed/nearby

---

## Database Model

User

↓

Business

↓

Update

Relationships:

* One User can own multiple Businesses
* One Business can have multiple Updates

---

## Local Setup

1. Clone Repository

git clone <repository-url>

2. Configure PostgreSQL

Create database:

localbuzz

3. Configure application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/localbuzz

spring.datasource.username=your_username

spring.datasource.password=your_password

4. Run Application

mvn spring-boot:run

---

## Project Status

Backend Version: V1 Complete

Completed Modules:

* Authentication
* Authorization
* Business Registration
* Admin Approval Workflow
* Business Updates
* Discovery Feed
* Map Discovery
* Feed Filtering
* Business Search
* Public Business Profile

---

## Future Enhancements

* Reviews & Ratings
* Image Uploads
* Bookmarks/Favorites
* Push Notifications
* Admin Dashboard
* Analytics & Insights
* Google Maps Route Integration

---

## Author

Pranav Patil
Pranav Patil
