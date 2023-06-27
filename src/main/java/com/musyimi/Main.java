package com.musyimi;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.musyimi.customer.Customer;
import com.musyimi.customer.CustomerRepository;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
	
	@Bean
	CommandLineRunner runner(CustomerRepository customerRepository) {
		
		return args -> {
			Customer poza = new Customer(
					"alas",
					"bro",
					"alasbro@gmail.com"
					);
			
			Customer mena = new Customer(
					"miw",
					"wiw",
					"miwiwi@gmail.com"
					);
			
			List<Customer> customers = List.of(poza, mena);
			customerRepository.saveAll(customers);
			
		};
	}
}
