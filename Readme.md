# Spring Boot Microservices Project

This is a demo microservices project built with Spring Boot. It includes:

- **Eureka Service Registry**
- **API Gateway with Spring Cloud Gateway**
- **User Service (sample microservice)**
- **Order Service (optional)**
- **Centralized Configuration via Spring Cloud Config**
- **Resilience4j (Retry, Circuit Breaker, Rate Limiter, Bulkhead)**

## 🔧 Tech Stack

- Java 17
- Spring Boot 3.5.x
- Spring Cloud 2025.0.x
- Eureka Discovery Server
- Spring Cloud Gateway
- Resilience4j
- Maven

## 🧱 Project Structure


springboot-microservices-demo/
│
├── api-gateway/ # API Gateway (port 8765)
├── eureka-server/ # Eureka Service Registry (port 8761)
├── user-service/ # Sample Microservice (port 8081)
├── order-service/ # Another Microservice (if created)
└── config-repo/ # Git repo for centralized config (optional)

markdown
Copy
Edit

## ▶️ How to Run

1. Start **Eureka Server** (`eureka-server`)
2. Start **API Gateway** (`api-gateway`)
3. Start **User Service** (`user-service`)
4. Test API calls through the gateway:
   - `http://localhost:8765/user/sample` (proxy to user-service)

## 🛡️ Resilience4j Features

You can find the following implementations in the `api-gateway`:
- ✅ Retry
- ✅ Circuit Breaker
- ✅ Rate Limiter
- ✅ Fallback APIs
