package com.react.burger.project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.react.burger.project.modal.Customer;

@Mapper
public interface CustomerMapper {
	
	Customer getCustomer(com.react.burger.project.entity.Customer dto);
}
