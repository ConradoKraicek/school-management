package com.empresa.schoolmanagement.repository;

import com.empresa.schoolmanagement.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}