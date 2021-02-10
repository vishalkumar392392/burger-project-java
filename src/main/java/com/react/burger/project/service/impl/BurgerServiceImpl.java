package com.react.burger.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.react.burger.project.entity.Customer;
import com.react.burger.project.modal.Order;
import com.react.burger.project.repository.BurgerRepository;
import com.react.burger.project.service.BurgerService;

@Service
public class BurgerServiceImpl implements BurgerService {

	@Autowired
	private BurgerRepository burgerRepository;

	@Override
	public List<Customer> getAllCustomers() {

		return burgerRepository.getAllCustomers();
	}

	@Override
	public String placeOrder(Order order) {
		
		List<String> ingreidients = new ArrayList<>();
		if(Objects.nonNull(order.getIngredients())) {
			if(order.getIngredients().getBacon()!=0) {
				ingreidients.add("bacon");
			}
			if(order.getIngredients().getCheese()!=0) {
				ingreidients.add("cheese");
			}
			if(order.getIngredients().getMeat()!=0) {
				ingreidients.add("meat");
			}
			if(order.getIngredients().getSalad()!=0) {
				ingreidients.add("salad");
			}
		}
		
		String food = ingreidients.stream().collect(Collectors.joining(","));
		return burgerRepository.placeOrder(order.getCustomer().getName(),order.getCustomer().getEmail(),
				order.getCustomer().getAddress().getCountry(),
				order.getCustomer().getAddress().getStreet(),
				order.getCustomer().getAddress().getZipcode(),
				order.getDelivery(),order.getPrice(),food);
	}
}
