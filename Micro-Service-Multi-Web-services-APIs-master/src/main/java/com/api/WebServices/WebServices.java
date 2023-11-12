package com.api.WebServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebServices {

	public static void main(String[] args) {
		SpringApplication.run(WebServices.class, args);
	}

//	@Bean
//	CommandLineRunner start(CustomerRepository cr){
//		return  args -> {
//			cr.save(Customer.builder().name("hassen").email("hassen@test.com").build());
//			cr.save(Customer.builder().name("ali").email("ali@test.com").build());
//			cr.save(Customer.builder().name("mostfa").email("mostfa@test.com").build());
//			cr.save(Customer.builder().name("arij").email("arij@test.com").build());
//		};
//	}

}
