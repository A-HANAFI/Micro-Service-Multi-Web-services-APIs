package com.api.WebServices.web;

import com.api.WebServices.entities.Customer;
import com.api.WebServices.mappers.CustomerMapper;
import com.api.WebServices.repository.CustomerRepository;
import com.api.WebServices.stub.CustomerServiceGrpc;
import com.api.WebServices.stub.CustomerServiceOuterClass;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@GrpcService

public class CustomerGrpcService extends CustomerServiceGrpc.CustomerServiceImplBase{

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerMapper customerMapper;
    @Override
    public void getAllCustomers(CustomerServiceOuterClass.GetAllCustomersRequest request, StreamObserver<CustomerServiceOuterClass.GetCustomersResponse> responseObserver) {
        List<Customer> customerList = customerRepository.findAll();
//        List<CustomerServiceOuterClass.Customer> grpcCustomers =
//                customerList.stream()
//                        .map(customerMapper::fromCustomer).collect(Collectors.toList());
        List<CustomerServiceOuterClass.Customer> grpcCustomers = new ArrayList<CustomerServiceOuterClass.Customer>();
        for (Customer c:customerList) {
            if(c.getName() != null){
            CustomerServiceOuterClass.Customer grpcCustomer = customerMapper.fromCustomer(c);
            grpcCustomers.add(grpcCustomer);
            }
        }

        CustomerServiceOuterClass.GetCustomersResponse customersResponse=
                CustomerServiceOuterClass.GetCustomersResponse.newBuilder()
                        .addAllCustomers(grpcCustomers)
                        .build();
        responseObserver.onNext(customersResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getCustomerById(CustomerServiceOuterClass.GetCustomerByIdRequest request, StreamObserver<CustomerServiceOuterClass.GetCustomerByIdResponse> responseObserver) {
        Customer customer = customerRepository.findById(request.getCustomerId()).get();
        CustomerServiceOuterClass.Customer grpcCustomer = customerMapper.fromCustomer(customer) ;
        CustomerServiceOuterClass.GetCustomerByIdResponse customerResponse=
                CustomerServiceOuterClass.GetCustomerByIdResponse.newBuilder()
                        .setCustomer(grpcCustomer)
                        .build();
        responseObserver.onNext(customerResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void saveCustomer(CustomerServiceOuterClass.SaveCustomerRequest request, StreamObserver<CustomerServiceOuterClass.SaveCustomerResponse> responseObserver) {
        CustomerServiceOuterClass.CustomerRequest customerRequest = request.getCustomer();
        Customer customer = customerRepository.save(customerMapper.fromGrpcCustomer(customerRequest));
        CustomerServiceOuterClass.SaveCustomerResponse customerResponse =
                CustomerServiceOuterClass.SaveCustomerResponse
                        .newBuilder()
                        .setCustomer(customerMapper.fromCustomer(customer))
                        .build();
        responseObserver.onNext(customerResponse);
        responseObserver.onCompleted();
    }
}
