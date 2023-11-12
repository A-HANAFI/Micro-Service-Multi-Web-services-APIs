package com.api.WebServices.repository;

import com.api.WebServices.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository  extends JpaRepository<Customer,Long> {

}
