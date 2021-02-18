package com.react.burger.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.react.burger.project.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

}
