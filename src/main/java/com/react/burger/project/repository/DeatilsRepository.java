package com.react.burger.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.react.burger.project.entity.Details;

@Repository
public interface DeatilsRepository extends JpaRepository<Details, Integer> {

	@Query(value = "{call get_customer_details()}", nativeQuery = true)
	List<Details> getDetails();

}
