 # Caliente-API

 ## Overview

 Caliente-API is a Java Spring Boot REST service implementing a layered architecture (Controller → Service → Repository) for managing Playlists and Songs. It includes DTOs, entity models, mappers, and service implementations to keep business logic and persistence concerns separated.

 ## Project Layout

 - `src/main/java/com/Caliente/api/controller` — REST controllers
 - `src/main/java/com/Caliente/api/service` — service interfaces and implementations
 - `src/main/java/com/Caliente/api/repository` — Spring Data repositories
 - `src/main/java/com/Caliente/api/dto` — request/response DTOs
 - `src/main/java/com/Caliente/api/mapper` — MapStruct mappers
 - `src/main/java/com/Caliente/api/config` — app configuration (CORS, etc.)
 - `src/main/resources` — Spring Boot profiles and application configuration

 ## Technologies

 - Java 17
 - Spring Boot (WebMVC, Data JPA, Validation)
 - Spring Data JPA
 - H2 (dev, in-memory)
 - PostgreSQL (prod)
 - MapStruct (mapping DTOs ↔ entities)
 - SpringDoc OpenAPI (Swagger UI)
 - Maven (build)
 - Docker, Docker Compose
 - GitHub Actions (CI)

 ## Configuration & Environment

 - Primary configuration files:
   - [src/main/resources/application.yaml](src/main/resources/application.yaml)
   - [src/main/resources/application-dev.yml](src/main/resources/application-dev.yml)
   - [src/main/resources/application-prod.yml](src/main/resources/application-prod.yml)

 - Copy `.env.example` → `.env` and populate secrets. `.env` is ignored by git (see `.gitignore`).

 Important env keys (examples located in `.env.example`):
 - `SPRING_PROFILES_ACTIVE` — `dev` or `prod`
 - `SERVER_PORT` — application port
 - `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASSWORD` — PostgreSQL connection for `prod`

 ## Running Locally (Dev)

 1. Ensure Java 17 and Maven are installed.
 2. Copy the example env: `cp .env.example .env` (Windows: copy or create `.env` manually) and adjust values.
 3. Run with Maven (default profile is `dev`):

 ```bash
 mvn -B spring-boot:run
 ```

 Dev profile uses an in-memory H2 database. Access H2 console at `http://localhost:8080/h2-console`.

 Swagger UI will be available at `http://localhost:8080/swagger-ui.html` once the app is running.

 ## Running with Docker (recommended for production-like environment)

 Build image (multi-stage Dockerfile provided):

 ```bash
 # build image
 docker build -t caliente-api:latest .

 # run container (example)
 docker run --env-file .env -p 8080:8080 caliente-api:latest
 ```

 ## Running with Docker Compose (Postgres + Backend)

 The repository includes `docker-compose.yml` which starts a PostgreSQL container and the backend. By default it sets `SPRING_PROFILES_ACTIVE=prod` for the backend service and injects DB connection info.

 Start services:

 ```bash
 docker-compose up --build
 ```

 The backend listens on port `8080` and connects to the `db` service. Customize credentials in environment variables or in `.env`.

 ## CI

 A GitHub Actions workflow is located at `.github/workflows/maven.yml` and runs `mvn -B clean verify` on pull requests and pushes to `main`/`master`.

 ## Swagger / API Docs

 SpringDoc is included and exposes the Swagger UI at `/swagger-ui.html`. Controllers will be auto-documented; adding `@Operation` and `@Tag` annotations improves readability.

 ## Notes & Next Steps

 - Add migrations with Flyway or Liquibase for production schema management.
 - Add health/readiness endpoints and container probes.
 - Add example API requests in the README (I can add Postman collection or curl examples if you want).

 ## Contact

 If you want, I can:
 - Add example requests and responses to this README.
 - Add Flyway and sample migration scripts.
 - Annotate controllers with OpenAPI descriptions for nicer docs.
