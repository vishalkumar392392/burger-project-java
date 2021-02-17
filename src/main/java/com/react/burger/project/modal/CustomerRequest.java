package com.react.burger.project.modal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class CustomerRequest {

	private String country;

	private String email;

	private String name;

	private String street;

	private String zipCode;

	private String delivery;
}
