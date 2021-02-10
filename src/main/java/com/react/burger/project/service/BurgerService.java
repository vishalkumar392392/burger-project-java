package com.react.burger.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.react.burger.project.entity.Customer;
import com.react.burger.project.modal.Order;

@Service
public interface BurgerService {

	public List<Customer> getAllCustomers();

	public String placeOrder(Order order);
}
