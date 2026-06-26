# LocalBuzz Backend

## Overview

LocalBuzz is a location-based community discovery platform that connects users with nearby businesses, local events, community announcements, and promotional offers.

The backend is built using **Spring Boot** and follows a **package-by-feature architecture**. It provides secure REST APIs for authentication, business management, community management, AI-powered content generation, administrative approval workflows, and personalized discovery feeds.

---

# Features

## Authentication & Authorization

* User Registration
* User Login
* JWT Authentication
* BCrypt Password Encryption
* Role-Based Access Control

### Supported Roles

* USER
* OWNER
* ADMIN

---

## Business Management

Business owners can:

* Register Businesses
* View Their Businesses
* Manage Business Information
* Publish Business Updates

### Supported Business Categories

* CAFE
* BAKERY
* RESTAURANT
* GYM
* SALON
* MEDICAL
* JEWELLERY
* OTHER

---

## Business Approval Workflow

All newly registered businesses require administrator approval before becoming publicly visible.

```
Owner
   │
Create Business
   │
Pending
   │
Admin Review
   │
Approved / Rejected
```

---

## Business Updates

Business owners can publish updates such as:

* OFFER
* EVENT
* PRODUCT
* SERVICE
* ANNOUNCEMENT

Updates are visible only for approved businesses.

---

## Community Module

Community administrators can:

* Create Community Posts
* Publish Local Notices
* Publish Community Events
* Publish Emergency Alerts

Community posts also follow an approval workflow before becoming visible.

---

## Discovery Feed

Users can explore:

* Latest Business Updates
* Community Announcements
* Local Events
* Promotional Offers

Supports filtering by:

* Business Category
* Update Type

---

## Nearby Business Discovery

Location-based APIs allow users to discover nearby businesses.

Features include:

* Radius Filtering
* Distance Calculation
* Latest Business Update
* Map-Friendly Response

---

## Business Search

Users can search businesses by:

* Business Name
* Business Category

---

## Public Business Profile

Each business profile includes:

* Business Details
* Address
* Category
* Description
* Recent Updates

---

## AI Features (Spring AI)

The backend integrates **Spring AI** to generate business content.

Available AI services:

* AI Business Description Generator
* AI Promotional Post Generator
* AI Community Announcement Generator

---

# Technology Stack

* Java 24
* Spring Boot
* Spring Security
* Spring AI
* JWT Authentication
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven

---

# Architecture

The project follows a **Package-by-Feature Architecture**.

```
Controller
      │
Service
      │
Repository
      │
Entity
```

### Project Modules

* admin
* ai
* business
* community
* config
* exception
* feed
* owner
* security
* update
* user

---

# Core API Modules

## User

* User Registration
* User Login
* User Profile

---

## Business

* Register Business
* View Owner Businesses
* Search Businesses
* Public Business Profile

---

## Updates

* Create Business Update
* View Business Updates

---

## Community

* Create Community Post
* View Community Feed

---

## Feed

* Discovery Feed
* Nearby Businesses

---

## Admin

* Approve Businesses
* Reject Businesses
* Approve Community Posts
* Reject Community Posts

---

## AI

* Generate Business Description
* Generate Promotional Post
* Generate Community Announcement

---

# Database Design

```
User
├── Business
│     └── Update
└── Community Post
```

Relationships

* One User can own multiple Businesses.
* One Business can publish multiple Updates.
* Community Administrators can publish Community Posts.

---

# Security

* JWT Authentication
* BCrypt Password Encoding
* Role-Based Authorization
* Protected REST APIs
* Stateless Authentication

---

# Local Setup

## Clone Repository

```bash
git clone <repository-url>
```

## Configure PostgreSQL

Create a database named:

```
localbuzz
```

## Configure application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/localbuzz
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## Run the Application

```bash
mvn spring-boot:run
```

---

# Project Status

**Version:** Complete

Implemented Modules

* Authentication & Authorization
* Business Management
* Business Approval Workflow
* Business Updates
* Community Module
* Discovery Feed
* Nearby Business Search
* Business Search
* Public Business Profiles
* Spring AI Integration
* Google Maps Ready APIs

---

# Future Enhancements

* Business Image Uploads
* Reviews & Ratings
* Bookmarks / Favorites
* Push Notifications
* Analytics Dashboard
* Business Insights

---

# Author

**Pranav Patil**
