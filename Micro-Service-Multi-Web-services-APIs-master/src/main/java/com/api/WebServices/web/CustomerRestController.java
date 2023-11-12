package com.api.WebServices.web;

import com.api.WebServices.entities.Customer;
import com.api.WebServices.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerRestController {
    CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> customerList(){
        return customerRepository.findAll();
    }

    @GetMapping("customers/{id}")
    public Customer getCustomer(@PathVariable Long id){
        return  customerRepository.findById(id).get();
    }

    @PostMapping("customers")
    public Customer addCustomer(@RequestBody Customer customer){
        return  customerRepository.save(customer);

    }
}
