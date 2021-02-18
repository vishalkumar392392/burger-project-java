package com.react.burger.project.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "address")
public class Address {

	@Id
	private int id;
	
	private String street;
	
	private String zipcode;
	
	private String country;
}
