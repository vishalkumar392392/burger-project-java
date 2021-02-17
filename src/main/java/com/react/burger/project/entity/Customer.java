package com.react.burger.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Customer {
	
	@Id
	private Integer id;
	
	@Column(name = "customer")
	private String name ;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phonenumber")
	private String phoneNumber;
	
	@Column(name = "address_id")
	private int addressId;
	
}
