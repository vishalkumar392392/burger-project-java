package com.react.burger.project.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity
public class Order {
	
	@Id
	private int id;
		
	private String delivery;
	

}
