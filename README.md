# Marketplace Platform

Marketplace Platform is a multi-service backend platform designed for building a scalable online marketplace ecosystem.  
The repository contains multiple microservices and shared libraries organized in a single monorepo structure.

---

## Architecture

The platform follows a **microservices architecture** combined with **Hexagonal (Ports and Adapters) architecture** inside each service. 
Shared functionality is provided via reusable internal libraries.

## Technologies

Key technologies used across the platform:

- Java 21
- Spring Boot
- Spring WebFlux
- Spring Data (R2DBC / JPA)
- PostgreSQL
- Maven multi-module builds
- Docker
- Liquibase (database migrations)
- MapStruct
- JUnit 5, Mockito, Testcontainers

## Services

### Catalog Service
Responsible for managing marketplace listings, including:

- listing creation and updates
- search and filtering
- preview images management
- publishing lifecycle

Additional services will be added as the platform evolves.

---

## Shared Libraries

Shared libraries located in the `libs/` directory provide:

- common error handling
- BOM dependency management

## Future roadmap

- additional domain services (orders, payments, users)
- event-driven communication expansion
- frontend applications
- centralized observability stack
