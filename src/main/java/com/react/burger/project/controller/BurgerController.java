package com.react.burger.project.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.react.burger.project.entity.Customer;
import com.react.burger.project.modal.Address;
import com.react.burger.project.modal.Ingredient;
import com.react.burger.project.modal.Order;
import com.react.burger.project.modal.OrderRequest;
import com.react.burger.project.modal.ResultModal;
import com.react.burger.project.modal.Results;
import com.react.burger.project.modal.TestIngredient;
import com.react.burger.project.service.BurgerService;

import springfox.documentation.spring.web.json.Json;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BurgerController {
	
	@Autowired
	private BurgerService burgerService;
	
	
	private int id = 100;
	
	private List<TestIngredient> list = new ArrayList<>();
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
		
		return burgerService.getAllCustomers();
	}
	
	@PostMapping(path = "/order")
	public String placeOrder(@RequestBody OrderRequest orderRequest) {
		
		Order order = new Order();
		com.react.burger.project.modal.Customer customer = new com.react.burger.project.modal.Customer();
		order.setIngredients(orderRequest.getIngredients());
		order.setPrice(orderRequest.getPrice());
		customer.setEmail(orderRequest.getCustomer().getEmail());
		customer.setName(orderRequest.getCustomer().getName());
		customer.setPhoneNumber(null);
		Address address = new Address();
		address.setCountry(orderRequest.getCustomer().getCountry());
		address.setStreet(orderRequest.getCustomer().getStreet());
		address.setZipCode(orderRequest.getCustomer().getZipCode());
		customer.setAddress(address);
		order.setCustomer(customer);
		order.setDelivery(orderRequest.getCustomer().getDelivery());
		order.setItems(orderRequest.getItems());
		return burgerService.placeOrder(order);
	}
	
	@GetMapping(path = "/ingredients")
	public Ingredient getIngredients() {
		return burgerService.getIngredients();
	}
	
	@GetMapping(path = "/order")
	public List<com.react.burger.project.entity.Order> getAllOrders() {
		return burgerService.getAllOrders();
	}
	
	@PostMapping(path = "/ing")
	public Integer addIng(
			@RequestBody TestIngredient ing) {
		int newId = id++;
		ing.setId(newId);
		list.add(ing);
		return newId;
	}
	
	@GetMapping(path = "/ing")
	public List<TestIngredient> getIng() {
        return list;
	}
	@GetMapping(path = "/ings")
	public List<TestIngredient> getIngQuery(@RequestParam(required = false,name = "title") String title) {
		if(Objects.nonNull(title)) {
			 return list.stream().filter(res->res.getTitle().contains(title)).collect(Collectors.toList());
		}
        return list;
	}
	
	@GetMapping(path = "/ing/{id}")
	public String delete(@PathVariable(value = "id") int id) {
		return "Success";
	}
	
	@GetMapping("/images")
	public void getImages() throws URISyntaxException, JsonMappingException, JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		URI url = new URI("https://randomuser.me/api/?results=50");
		
		ResponseEntity<String> res = restTemplate.getForEntity(url, String.class);
		ResultModal results = mapper.readValue(res.getBody(), ResultModal.class);
		List<Results> list = results.getResults();
		List<String> pics =  list.stream().map(x->x.getPicture().getLarge()).collect(Collectors.toList());
	}
	
	

	

}
