package com.react.burger.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.react.burger.project.entity.Customer;
import com.react.burger.project.modal.Order;
import com.react.burger.project.repository.BurgerRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BurgerController {
	
	@Autowired
	private BurgerRepository burgerRepository;
	
	@GetMapping("/customers")
	public List<Customer> getAllBurger() {
		
		return burgerRepository.getAllCustomers();
	}
	
	@PostMapping(path = "/order")
	public Object placeOrder(@RequestBody Order order) {
		
		return null;
	}
	

}
