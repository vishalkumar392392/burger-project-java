package com.react.burger.project.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Ingredient {
	
	@Id
	private int id;
	
	private String salad;
	
	private String bacon;
	
	private String cheese;
	
	private String meat;

}
