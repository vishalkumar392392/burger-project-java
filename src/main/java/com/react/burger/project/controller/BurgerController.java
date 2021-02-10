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
import com.react.burger.project.service.BurgerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BurgerController {
	
	@Autowired
	private BurgerService burgerService;
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
		
		return burgerService.getAllCustomers();
	}
	
	@PostMapping(path = "/order")
	public String placeOrder(@RequestBody Order order) {
		return burgerService.placeOrder(order);
	}
	

}
