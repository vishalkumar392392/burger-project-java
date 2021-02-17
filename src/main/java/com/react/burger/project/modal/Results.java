package com.react.burger.project.modal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Results {

	private String gender;
	private String email;

	private Picture picture;
}
