# Project Diagrams

This folder contains the initial planning and design artifacts for the **StayEase Backend** project. Before writing any code, these diagrams were created to visualize the application's architecture, identify the core business entities, define user interactions, and establish an initial API contract.

The purpose of these diagrams is to reduce ambiguity during development and provide a clear roadmap for implementing the system. They represent the project's current understanding and are expected to evolve as new requirements are discovered during implementation.

---

## Entity Relationship (ER) Diagram

The ER diagram represents the initial database schema for the application. It identifies the primary entities involved in the hotel booking system, the attributes stored within each entity, and the relationships between them.

Some important design decisions reflected in this diagram include:

- The **Room** entity represents a **room type** (e.g., Standard Room, Deluxe Room, Presidential Suite) rather than an individual physical room.
- Room availability is managed separately through the **Inventory** entity, which stores the number of available rooms for each room type on a particular date.
- A booking is linked to users, guests, hotels, room types, and payments to represent the complete booking lifecycle.

This database design serves as the starting point and may be refined during implementation.

---

## Hotel Manager Use Case

This diagram illustrates how a hotel manager interacts with the system.

The primary responsibilities of a hotel manager include:

- Creating new hotels
- Defining room types for each hotel
- Updating hotel and room information
- Managing room inventory
- Viewing and managing bookings

The objective of this diagram is to define the scope of functionality available to hotel managers before implementation begins.

---

## Guest Search & Booking Use Case

This diagram describes the primary workflow from a guest's perspective.

The guest can:

- Search hotels using filters such as city, check-in date, check-out date, and number of rooms.
- View hotel details and available room types.
- Create a booking for the selected room type.
- Add guest information to the booking.
- Complete payment.
- View and manage existing bookings.

This flow serves as the foundation for the application's booking process and helps identify the APIs and services required for implementation.

---

## Domain Model Overview

The domain model provides a high-level overview of the major entities in the system and their interactions.

It combines both structural and functional aspects of the application by showing:

- User roles
- Hotel and room management
- Inventory management
- Booking flow
- Guest information
- Payment processing

The diagram also outlines the complete booking lifecycle, beginning with hotel search and ending with booking confirmation after successful payment.

---

## Dynamic Pricing Design

The application supports dynamic room pricing based on multiple business rules rather than relying solely on a fixed room price.

The initial pricing model considers factors such as:

- Base room price
- Room occupancy
- Booking urgency
- Holidays and seasonal demand
- Promotional discounts

During the design phase, different design patterns were evaluated. The current approach favors the **Decorator Pattern** because multiple pricing rules may need to be applied together to produce the final room price. This decision may be revisited as development progresses.

---

## Initial API Design

This diagram presents the initial REST API blueprint for the application.

The endpoints are grouped according to different actors in the system:

- Hotel Manager APIs
- Guest APIs
- Authentication APIs
- Internal System APIs

The API list represents the intended functionality during the planning phase. As implementation continues, some endpoints may be modified, removed, or expanded based on practical requirements and project evolution.

---

## Notes

These diagrams are planning artifacts and should not be considered final documentation. As the project evolves, the database schema, system architecture, workflows, APIs, and implementation details may change to better accommodate new requirements, optimizations, and design improvements.

The diagrams will be updated throughout the development lifecycle to keep them aligned with the actual implementation.