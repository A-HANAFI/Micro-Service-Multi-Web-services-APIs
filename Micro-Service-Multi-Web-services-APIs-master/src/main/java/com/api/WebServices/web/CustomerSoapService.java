package com.api.WebServices.web;

import com.api.WebServices.entities.Customer;
import com.api.WebServices.entities.CustomerRequest;
import com.api.WebServices.mappers.CustomerMapper;
import com.api.WebServices.repository.CustomerRepository;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@WebService(serviceName = "customerWS")
public class CustomerSoapService {
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    @WebMethod(operationName = "customerList")
        public List<Customer> customerList () {
            return customerRepository.findAll();
        }

    @WebMethod
    public Customer getCustomerById(@WebParam(name="id") Long id){
        return customerRepository.findById(id).get();
    }

    @WebMethod
    public Customer saveCustomer ( @WebParam(name="customer") CustomerRequest customerRequest){
        Customer customer = customerMapper.from(customerRequest);
        return customerRepository.save(customer);
    }
}