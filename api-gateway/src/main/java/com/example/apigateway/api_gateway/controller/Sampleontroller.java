package com.example.apigateway.api_gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class Sampleontroller {
	
    private int attempt = 1;

    @GetMapping("/sample-api")
//    @Retry(name = "sample-api", fallbackMethod = "fallbackMethod")
//    @CircuitBreaker(name = "sample-api", fallbackMethod = "sampleFallback")
    @RateLimiter(name = "default", fallbackMethod = "rateLimitFallback")
    public String sampleApi() {
        System.out.println("Call attempt: " + attempt++);
        if (Math.random() < 0.7) {
            throw new RuntimeException("Sample API failed");
        }
        return "Sample API success!";
    }

    public String fallbackMethod(Exception e) {
        return "Fallback response after retries: " + e.getMessage();
    }
    
    public ResponseEntity<String> rateLimitFallback(RequestNotPermitted ex) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body("Too many requests. Please wait and try again later.");
    }
}
