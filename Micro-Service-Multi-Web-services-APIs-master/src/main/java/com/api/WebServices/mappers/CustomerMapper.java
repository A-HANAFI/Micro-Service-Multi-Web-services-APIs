package com.api.WebServices.mappers;

import com.api.WebServices.entities.Customer;
import com.api.WebServices.entities.CustomerRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.api.WebServices.stub.CustomerServiceOuterClass;

@Component
public class CustomerMapper {
    private ModelMapper modelMapper = new ModelMapper();
    public Customer from(CustomerRequest customerRequest){
       return  modelMapper.map(customerRequest,Customer.class);

    }

    public CustomerServiceOuterClass.Customer fromCustomer(Customer customer){
        return modelMapper.map(customer, CustomerServiceOuterClass.Customer.Builder.class).build();
    }
//       public CustomerServiceOuterClass.Customer fromCustomer(Customer customer){
//        return modelMapper.map(customer, CustomerServiceOuterClass.Customer.Builder.class).build();
//    }

    public Customer fromGrpcCustomer( CustomerServiceOuterClass.CustomerRequest customerRequest){
        return modelMapper.map(customerRequest, Customer.class);
    }
}
