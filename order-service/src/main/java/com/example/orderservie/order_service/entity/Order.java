package com.example.orderservie.order_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private Long userId;
	
	private String productName;
	
	private Double amount;

	public Order(Long id, Long userId, String productName, Double amount) {
		
		Id = id;
		this.userId = userId;
		this.productName = productName;
		this.amount = amount;
	}

	public Order() {
		
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
}
