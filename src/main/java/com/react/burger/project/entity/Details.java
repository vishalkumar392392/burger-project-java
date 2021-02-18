package com.react.burger.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Details {
	
	@Id
	private int id;
	
	private String customer;
	
	private String email;
	
	private String phonenumber;
	
	@Column(name = "customer_address")
	private String customerAddress;
	
	private String orders;

}
