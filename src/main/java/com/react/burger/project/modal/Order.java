package com.react.burger.project.modal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Order {
	
	private int id;
	
	@JsonProperty("deliveryMethod")
	private String delivery;
	
	private int price;
	
	private Ingredient ingredients;
	
	private Customer customer;
	
	

}
