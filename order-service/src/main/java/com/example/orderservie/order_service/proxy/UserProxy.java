package com.example.orderservie.order_service.proxy;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.orderservie.order_service.dto.User;

//@FeignClient(name="user-service", url="http://localhost:8081")
@FeignClient(name="user-service")
public interface UserProxy {

	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable Long id);
}
