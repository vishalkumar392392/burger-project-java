package com.react.burger.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.react.burger.project.entity.Customer;

@Repository
public interface BurgerRepository extends JpaRepository<Customer, Integer> {

	@Query(value = "select * from customer", nativeQuery = true)
	public List<Customer> getAllCustomers();

	@Query(value = "{call placeOrder(:name,:email,:country,:street,:zipcode,:delivery,:price,:food)}"
			, nativeQuery = true)
	public String placeOrder(@Param("name") String name,@Param("email")  String email,
			@Param("country")  String country,
			@Param("street") String street,
			@Param("zipcode") String zipcode,@Param("delivery")  String delivery,
			@Param("price") float price,@Param("food")  String food);

}
