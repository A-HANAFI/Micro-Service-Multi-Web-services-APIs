package com.api.WebServices.web;

import com.api.WebServices.entities.Customer;
import com.api.WebServices.entities.CustomerRequest;
import com.api.WebServices.mappers.CustomerMapper;
import com.api.WebServices.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class CustomerGraphqlController {
    private CustomerRepository  customerRepository;
    private CustomerMapper customerMapper ;
    @QueryMapping
    public List<Customer> allCustomers(){
        return  customerRepository.findAll();
    }
    
    @QueryMapping
    public Customer customerById(@Argument Long id){
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer == null) throw new RuntimeException(String.format("Customer %s not found",id));
        return customer;
    }

    @MutationMapping
    public  Customer saveCustomer(@Argument CustomerRequest customerRequest){

        Customer customer = customerMapper.from(customerRequest);
        return customerRepository.save(customer);
    }
}
