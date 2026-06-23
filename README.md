# LocalBuzz Backend

LocalBuzz is a community discovery platform that helps users discover nearby businesses, offers, events, and updates in their locality.

The platform enables business owners to register their businesses, publish updates, and allows users to stay informed about what's happening around them.

## Tech Stack

* Java 21
* Spring Boot
* Spring Security
* JWT Authentication
* PostgreSQL
* Spring Data JPA / Hibernate
* Maven

## Features

### Authentication & Authorization

* User Registration
* User Login
* BCrypt Password Encryption
* JWT-based Authentication
* Role-Based Authorization

Supported Roles:

* USER
* OWNER
* ADMIN

### Business Management

Business owners can:

* Create Businesses
* View Their Businesses
* Manage Business Information

Business Categories include:

* Restaurant
* Cafe
* Gym
* Medical
* Education
* Retail
* And more

### Business Approval Workflow

All businesses require approval before becoming public.

Workflow:

Owner Registers Business

↓

Business Status = PENDING

↓

Admin Reviews Business

↓

APPROVED / REJECTED

### Update Management

Business owners can publish:

* Offers
* Events
* Product Launches
* Service Updates
* Announcements

Only the owner of a business can create updates for that business.

Users can view updates published by businesses.

## API Endpoints

### Authentication

| Method | Endpoint        | Description   |
| ------ | --------------- | ------------- |
| POST   | /users/register | Register User |
| POST   | /users/login    | Login User    |

### Business Module

| Method | Endpoint       | Description          |
| ------ | -------------- | -------------------- |
| POST   | /businesses    | Create Business      |
| GET    | /businesses/my | Get Owner Businesses |

### Admin Module

| Method | Endpoint                       | Description      |
| ------ | ------------------------------ | ---------------- |
| POST   | /admin/businesses/{id}/approve | Approve Business |
| POST   | /admin/businesses/{id}/reject  | Reject Business  |

### Update Module

| Method | Endpoint                 | Description          |
| ------ | ------------------------ | -------------------- |
| POST   | /updates                 | Create Update        |
| GET    | /businesses/{id}/updates | Get Business Updates |

## Database

Main Entities:

* User
* Business
* Update

Relationships:

User (OWNER)
→ Business
→ Update

## Project Workflow

OWNER Register

↓

OWNER Login

↓

Create Business

↓

PENDING Approval

↓

ADMIN Approves Business

↓

Business Becomes Active

↓

OWNER Creates Updates

↓

Users View Updates

## Future Enhancements

* Discovery Feed
* Radius-Based Search
* Location-Based Recommendations
* Business Reviews
* Business Analytics
* Media Upload Support
* Advanced Search & Filtering

## Project Status

Current Phase: Phase 5 - Update Module Completed

Completed Modules:

* Authentication
* Authorization
* Business Management
* Admin Approval Workflow
* Update Management

Upcoming Module:

* Discovery Feed

## Author

Pranav Patil
