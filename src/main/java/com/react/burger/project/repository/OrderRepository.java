package com.react.burger.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.react.burger.project.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query(value = "select o.id,o.ingredients,o.price,o.delivery,o.customer_id from orderburger o order by o.price desc",nativeQuery = true)
	List<Order> getAllOrders();
}
