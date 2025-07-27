package com.example.userservice.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userservice.user_service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
