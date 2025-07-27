package com.example.userservice.user_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.user_service.entity.User;
import com.example.userservice.user_service.repository.UserRepository;

@RestController
public class UserController {
	
	private UserRepository repository;
	
	
	public UserController(UserRepository repository) {
		
		this.repository = repository;
	}


	@GetMapping("/users")
	public List<User> getAllUsers(){
		return repository.findAll();
		
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable Long id){
		Optional<User> userOptional=repository.findById(id);
		return userOptional;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user){
		User savedUser=repository.save(user);
//		return ResponseEntity.created(null).build();
		return new ResponseEntity<User>(savedUser,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id){
		repository.deleteById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User updateUser) {
		return repository.findById(id)
				.map(user->{
					user.setEmail(updateUser.getEmail());
					user.setName(updateUser.getName());
					User savedUser = repository.save(user);
					return  new ResponseEntity<>(savedUser,HttpStatus.OK);
					
				})
				.orElse(null);
		
	}
	

}
