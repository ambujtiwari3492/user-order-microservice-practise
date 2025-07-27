package com.example.orderservie.order_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.orderservie.order_service.dto.User;
import com.example.orderservie.order_service.entity.Order;
import com.example.orderservie.order_service.proxy.UserProxy;
import com.example.orderservie.order_service.repository.OrderRepository;

@RestController
public class OrderController {
	
	private OrderRepository repository;
	private final RestTemplate restTemplate;
	private UserProxy proxy;
	
	public OrderController(OrderRepository repository, RestTemplate restTemplate, UserProxy proxy) {
		
		this.repository = repository;
		this.restTemplate = restTemplate;
		this.proxy = proxy;
	}

	

	@GetMapping("/orders")
	public List<Order> getAllOrders(){
		return repository.findAll();
	}
	
	@PostMapping("/orders")
	public ResponseEntity<Order> createOrder(@RequestBody Order order){
		Order savedOrder=repository.save(order);
		return new ResponseEntity<Order>(savedOrder,HttpStatus.CREATED);
	}
	
//	@GetMapping("/orders/{id}")
//	public Optional<Order> getOrderById(@PathVariable Long id){
//		Optional<Order> orderOptional = repository.findById(id);
//		return orderOptional;
//	}
	
	
	@GetMapping("/orders/{id}")
	public ResponseEntity<User> getUserForOrder(@PathVariable Long id){
		Optional<Order> orOptional= repository.findById(id);
		if(orOptional.isPresent()) {
			Order order = orOptional.get();
            Long userId = order.getUserId();
			User user = restTemplate.getForObject("http://localhost:8081/users/"+ order.getUserId(), User.class);
			return ResponseEntity.ok(user);
		}
		else {
			 return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/orders/feign/{id}")
	public ResponseEntity<User> getUserForOrderFeign(@PathVariable Long id){
		Optional<Order> orderOptional=repository.findById(id);
		if(orderOptional.isPresent()) {
			Order order =orderOptional.get();
			Long userIdLong=order.getUserId();
			Optional<User> user=proxy.getUserById(userIdLong);
			if(user.isPresent()) {
				return new ResponseEntity<User>(user.get(),HttpStatus.OK);
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}

}
