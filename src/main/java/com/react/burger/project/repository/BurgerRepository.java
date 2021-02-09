package com.react.burger.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.react.burger.project.entity.Customer;

@Repository
public interface BurgerRepository extends JpaRepository<Customer, Integer> {
	
	
	@Query(value ="select * from customer",nativeQuery = true)
	public List<Customer> getAllCustomers();

}
