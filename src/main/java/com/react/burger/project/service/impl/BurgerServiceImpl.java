package com.react.burger.project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.react.burger.project.entity.Address;
import com.react.burger.project.entity.Customer;
import com.react.burger.project.entity.Details;
import com.react.burger.project.modal.Ingredient;
import com.react.burger.project.modal.Order;
import com.react.burger.project.repository.AddressRepository;
import com.react.burger.project.repository.BurgerRepository;
import com.react.burger.project.repository.DeatilsRepository;
import com.react.burger.project.repository.OrderRepository;
import com.react.burger.project.service.BurgerService;

@Service
public class BurgerServiceImpl implements BurgerService {

	@Autowired
	private BurgerRepository burgerRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private DeatilsRepository detailsRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<Customer> getAllCustomers() {

		return burgerRepository.getAllCustomers();
	}

	@Override
	public String placeOrder(Order order) {
		
		String food = null;
		List<String> ingreidients = new ArrayList<>();
		if(Objects.nonNull(order.getIngredients())) {
			if(order.getIngredients().getBacon()!=0) {
				ingreidients.add("Bacon("+order.getIngredients().getBacon()+")");
			}
			if(order.getIngredients().getCheese()!=0) {
				ingreidients.add("Cheese("+order.getIngredients().getCheese()+")");
			}
			if(order.getIngredients().getMeat()!=0) {
				ingreidients.add("Meat("+order.getIngredients().getMeat()+")");
			}
			if(order.getIngredients().getSalad()!=0) {
				ingreidients.add("Salad("+order.getIngredients().getSalad()+")");
			}
			food = ingreidients.stream().collect(Collectors.joining(","));
		}
		
		 food = order.getItems();
		float val = Float.parseFloat(order.getPrice());
		return burgerRepository.placeOrder(order.getCustomer().getName(),order.getCustomer().getEmail(),
				order.getCustomer().getAddress().getCountry(),
				order.getCustomer().getAddress().getStreet(),
				order.getCustomer().getAddress().getZipCode(),
				order.getDelivery(),val,food);
	}

	@Override
	public Ingredient getIngredients() {

		return new Ingredient();
	}

	@Override
	public List<com.react.burger.project.entity.Order> getAllOrders() {

		return orderRepository.getAllOrders();
	}

	@Override
	public List<Details> getDetails() {
		
		return detailsRepository.getDetails();
	}

	@Override
	public List<Address> getAllAddress() {
		return addressRepository.findAll();
	}
}
