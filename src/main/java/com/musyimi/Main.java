package com.musyimi;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;
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
			Faker faker = new Faker();
			Customer customer = new Customer(
					faker.name().firstName(),
					faker.name().lastName(),
					faker.internet().safeEmailAddress()
					);
			customerRepository.save(customer);
			
		};
	}
}
