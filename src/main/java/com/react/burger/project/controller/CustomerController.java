package com.react.burger.project.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.react.burger.project.modal.Customer;
import com.react.burger.project.service.BurgerService;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = CustomerController.API.ROOT)
public class CustomerController {

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static final class API {

		public static final String ROOT = "/customer";
		public static final String CUSTOMERS = "/customers";
	}

	private final BurgerService burgerService;

	@GetMapping(path = API.CUSTOMERS,produces = MediaType.APPLICATION_JSON_VALUE)
	public String getCustomers() throws JsonProcessingException {

		
		return burgerService.getCustomers();
	}

}
