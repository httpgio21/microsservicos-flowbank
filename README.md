# Microservices example
Two Spring Boot services (Maven multi-module):
- usuario-service (port 8080) - Basic Auth
- conta-service (port 8081)  - JWT filter (simple)

How to run:
1. Build the project: `mvn -pl conta-service -am spring-boot:run`
2. In another terminal: `mvn -pl usuario-service -am spring-boot:run`

Notes:
- This is a skeleton. JWT token generation endpoint is not included; JwtFilter accepts requests without token only if no Authorization header is provided.
- Adjust secrets and password encoders for production.
