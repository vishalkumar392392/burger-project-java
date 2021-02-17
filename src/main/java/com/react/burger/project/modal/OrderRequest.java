package com.react.burger.project.modal;

import lombok.Data;

@Data
public class OrderRequest {

	private String price;

	private Ingredient ingredients;
	
	private String items;
	
	private CustomerRequest customer;

}
