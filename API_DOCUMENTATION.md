# LocalBuzz Backend API Documentation

Version: 1.0

Backend: Spring Boot

Last Updated: June 2026
  # LocalBuzz Backend API Documentation

This document describes the REST APIs provided by the LocalBuzz Backend.

---

# Table of Contents

* Base URL
* Authentication
* User APIs
* Business APIs
* Update APIs
* Community APIs
* Feed APIs
* Admin APIs
* AI APIs
* HTTP Status Codes
* Authentication Flow

---

# Base URL

```text
http://localhost:8081
```

---

# Authentication

The backend uses **JWT (JSON Web Token)** authentication for all protected endpoints.

Include the following header with every authenticated request:

```http
Authorization: Bearer <JWT_TOKEN>
```

> **Note:** Public endpoints do not require a JWT token.

---

# User APIs

Base Path

```text
/users
```

| Method | Endpoint          | Authentication | Description                                 |
| ------ | ----------------- | -------------- | ------------------------------------------- |
| POST   | `/users/register` | No             | Register a new user                         |
| POST   | `/users/login`    | No             | Authenticate a user and receive a JWT token |
| GET    | `/users/me`       | Yes            | Get the authenticated user's profile        |
| GET    | `/users/profile`  | Yes            | Get the authenticated user's name           |

---

## Register User

**Endpoint**

```http
POST /users/register
```

### Request Body

```json
{
  "name": "Pranav Patil",
  "email": "pranav@gmail.com",
  "password": "password123",
  "role": "USER"
}
```

### Success Response

```json
{
  "id": 1,
  "name": "Pranav Patil",
  "email": "pranav@gmail.com"
}
```

---

## Login

**Endpoint**

```http
POST /users/login
```

### Request Body

```json
{
  "email": "pranav@gmail.com",
  "password": "password123"
}
```

### Success Response

```json
{
  "token": "JWT_TOKEN"
}
```

---

## Get Logged-in User

**Endpoint**

```http
GET /users/me
```

### Headers

```http
Authorization: Bearer <JWT_TOKEN>
```

### Success Response

Returns the authenticated user's profile information.

---

# Business APIs

Base Path

```text
/businesses
```

| Method | Endpoint                               | Authentication | Description                                         |
| ------ | -------------------------------------- | -------------- | --------------------------------------------------- |
| POST   | `/businesses`                          | Yes (OWNER)    | Register a new business                             |
| GET    | `/businesses/my`                       | Yes (OWNER)    | Get all businesses owned by the authenticated owner |
| GET    | `/businesses/search?keyword={keyword}` | No             | Search approved businesses                          |
| GET    | `/businesses/{id}`                     | No             | Get a public business profile                       |

---

## Register Business

**Endpoint**

```http
POST /businesses
```

### Headers

```http
Authorization: Bearer <JWT_TOKEN>
```

### Request Body

```json
{
  "name": "The Daily Grind",
  "description": "Best coffee in town",
  "address": "Main Road",
  "category": "CAFE",
  "phone": "9876543210",
  "website": "https://dailygrind.com",
  "latitude": 19.2183,
  "longitude": 72.9781
}
```

### Success Response

Returns the newly created business details.

---

## Get My Businesses

**Endpoint**

```http
GET /businesses/my
```

### Headers

```http
Authorization: Bearer <JWT_TOKEN>
```

### Success Response

Returns all businesses owned by the authenticated owner.

---

## Search Businesses

**Endpoint**

```http
GET /businesses/search?keyword=cafe
```

### Success Response

Returns a list of approved businesses matching the supplied keyword.

---

## Get Public Business Profile

**Endpoint**

```http
GET /businesses/{id}
```

### Success Response

Returns:

* Business information
* Category
* Address
* Description
* Contact information
* Recent business updates
---

# Update APIs

Base Path

```text
/updates
```

| Method | Endpoint                   | Authentication | Description                    |
| ------ | -------------------------- | -------------- | ------------------------------ |
| POST   | `/updates`                 | Yes (OWNER)    | Create a new business update   |
| GET    | `/businesses/{id}/updates` | No             | Get all updates for a business |

---

## Create Business Update

**Endpoint**

```http
POST /updates
```

### Headers

```http
Authorization: Bearer <JWT_TOKEN>
```

### Request Body

```json
{
  "businessId": 1,
  "title": "Weekend Offer",
  "description": "Flat 20% off on all beverages.",
  "type": "OFFER"
}
```

### Success Response

Returns the created business update.

---

## Get Business Updates

**Endpoint**

```http
GET /businesses/{id}/updates
```

### Success Response

Returns all updates published by the specified business.

---

# Community APIs

Base Path

```text
/community
```

| Method | Endpoint                                         | Authentication        | Description                |
| ------ | ------------------------------------------------ | --------------------- | -------------------------- |
| POST   | `/community`                                     | Yes (COMMUNITY_ADMIN) | Create a community post    |
| GET    | `/community?lat={lat}&lng={lng}&radius={radius}` | No                    | Get nearby community posts |
| DELETE | `/community/{id}`                                | Yes (COMMUNITY_ADMIN) | Delete a community post    |

---

## Create Community Post

**Endpoint**

```http
POST /community
```

### Headers

```http
Authorization: Bearer <JWT_TOKEN>
```

### Request Body

```json
{
  "title": "Community Clean-up Drive",
  "description": "Join us this Sunday for a neighborhood clean-up event.",
  "type": "EVENT",
  "latitude": 19.2183,
  "longitude": 72.9781
}
```

### Success Response

Returns the created community post.

---

## Get Community Posts

**Endpoint**

```http
GET /community?lat=19.2183&lng=72.9781&radius=10
```

### Success Response

Returns all community posts available within the specified search radius.

---

## Delete Community Post

**Endpoint**

```http
DELETE /community/{id}
```

### Headers

```http
Authorization: Bearer <JWT_TOKEN>
```

### Success Response

Deletes the specified community post.

---

# Feed APIs

Base Path

```text
/feed
```

| Method | Endpoint       | Authentication | Description                          |
| ------ | -------------- | -------------- | ------------------------------------ |
| GET    | `/feed`        | No             | Retrieve the latest business updates |
| GET    | `/feed/nearby` | No             | Retrieve nearby approved businesses  |

---

## Get Feed

**Endpoint**

```http
GET /feed
```

### Optional Query Parameters

| Parameter  | Description                             |
| ---------- | --------------------------------------- |
| `category` | Filter by business category             |
| `type`     | Filter by update type                   |
| `search`   | Search by business name or update title |

### Example Requests

```http
GET /feed
```

```http
GET /feed?category=CAFE
```

```http
GET /feed?type=OFFER
```

```http
GET /feed?search=coffee
```

```http
GET /feed?category=CAFE&type=OFFER
```

### Success Response

Returns a list of feed items matching the applied filters.

---

## Get Nearby Businesses

**Endpoint**

```http
GET /feed/nearby
```

### Query Parameters

| Parameter | Description                 |
| --------- | --------------------------- |
| `lat`     | User latitude               |
| `lng`     | User longitude              |
| `radius`  | Search radius in kilometers |

### Example Request

```http
GET /feed/nearby?lat=19.2183&lng=72.9781&radius=10
```

### Success Response

Returns nearby approved businesses along with their latest updates and calculated distance.
---

# Admin APIs

Base Path

```text
/admin/businesses
```

| Method | Endpoint                         | Authentication | Description                |
| ------ | -------------------------------- | -------------- | -------------------------- |
| POST   | `/admin/businesses/{id}/approve` | Yes (ADMIN)    | Approve a pending business |
| POST   | `/admin/businesses/{id}/reject`  | Yes (ADMIN)    | Reject a pending business  |

---

## Approve Business

**Endpoint**

```http
POST /admin/businesses/{id}/approve
```

### Headers

```http
Authorization: Bearer <JWT_TOKEN>
```

### Path Variable

| Parameter | Description |
| --------- | ----------- |
| `id`      | Business ID |

### Success Response

Returns the updated business details with the **APPROVED** status.

---

## Reject Business

**Endpoint**

```http
POST /admin/businesses/{id}/reject
```

### Headers

```http
Authorization: Bearer <JWT_TOKEN>
```

### Path Variable

| Parameter | Description |
| --------- | ----------- |
| `id`      | Business ID |

### Success Response

Returns the updated business details with the **REJECTED** status.

---

# AI APIs

Base Path

```text
/api/ai
```

| Method | Endpoint                       | Authentication | Description                                   |
| ------ | ------------------------------ | -------------- | --------------------------------------------- |
| POST   | `/api/ai/business-description` | Yes            | Generate an AI-powered business description   |
| POST   | `/api/ai/promotional-post`     | Yes            | Generate an AI promotional post               |
| POST   | `/api/ai/community-post`       | Yes            | Generate an AI-powered community announcement |

---

## Generate Business Description

**Endpoint**

```http
POST /api/ai/business-description
```

### Headers

```http
Authorization: Bearer <JWT_TOKEN>
```

### Request Body

```json
{
  "businessName": "The Daily Grind",
  "category": "CAFE",
  "services": "Coffee, Cakes, Sandwiches"
}
```

### Success Response

```json
{
  "content": "The Daily Grind is a cozy neighborhood cafe offering freshly brewed coffee, delicious cakes, and handcrafted sandwiches in a warm and welcoming atmosphere."
}
```

---

## Generate Promotional Post

**Endpoint**

```http
POST /api/ai/promotional-post
```

### Headers

```http
Authorization: Bearer <JWT_TOKEN>
```

### Request Body

```json
{
  "businessName": "The Daily Grind",
  "offer": "20% OFF",
  "details": "Available this weekend only."
}
```

### Success Response

```json
{
  "content": "🎉 Weekend Special! Enjoy 20% OFF on your favorite coffee and snacks at The Daily Grind. Offer valid this weekend only."
}
```

---

## Generate Community Announcement

**Endpoint**

```http
POST /api/ai/community-post
```

### Headers

```http
Authorization: Bearer <JWT_TOKEN>
```

### Request Body

```json
{
  "title": "Tree Plantation Drive",
  "description": "Join us this Sunday to plant trees in our locality."
}
```

### Success Response

```json
{
  "content": "🌳 Join our Tree Plantation Drive this Sunday! Let's work together to make our community greener and healthier."
}
```
---

# HTTP Status Codes

| Status Code                   | Description                                                    |
| ----------------------------- | -------------------------------------------------------------- |
| **200 OK**                    | Request completed successfully                                 |
| **201 Created**               | Resource created successfully                                  |
| **400 Bad Request**           | Invalid request body or parameters                             |
| **401 Unauthorized**          | Authentication required or invalid JWT token                   |
| **403 Forbidden**             | User does not have permission to access the requested resource |
| **404 Not Found**             | Requested resource does not exist                              |
| **500 Internal Server Error** | Unexpected server error                                        |

---

# Authentication Flow

1. Register a new user.

```http
POST /users/register
```

2. Login using your credentials.

```http
POST /users/login
```

3. The server returns a JWT token.

Example Response

```json
{
  "token": "JWT_TOKEN"
}
```

4. Include the JWT token in the Authorization header for all protected endpoints.

```http
Authorization: Bearer <JWT_TOKEN>
```

5. Access protected APIs such as:

* Create Business
* View My Businesses
* Create Business Update
* Create Community Post
* Approve/Reject Businesses
* AI Content Generation
* View User Profile

---

# API Modules Summary

| Module    | Base Path           |
| --------- | ------------------- |
| User      | `/users`            |
| Business  | `/businesses`       |
| Update    | `/updates`          |
| Community | `/community`        |
| Feed      | `/feed`             |
| Admin     | `/admin/businesses` |
| AI        | `/api/ai`           |

---

# Technologies Used

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

# Notes

* All protected endpoints require a valid JWT token.
* Business registration requires administrator approval before appearing publicly.
* Only approved businesses are visible in search results and feeds.
* AI endpoints generate business descriptions, promotional posts, and community announcements using Spring AI.
* Nearby business and community APIs use latitude, longitude, and radius for location-based discovery.

---

# Version

**API Version:** 1.0

**Backend Version:** LocalBuzz Backend V1

**Last Updated:** June 2026

---

# Author

**Pranav Patil**
