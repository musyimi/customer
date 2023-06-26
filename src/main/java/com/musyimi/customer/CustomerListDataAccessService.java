package com.musyimi.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDao {
	

	private static List<Customer> customers; 
	
	static {
		customers = new ArrayList<>(); 
		
		Customer poza = new Customer(
				1,
				"Poza",
				"Umeze",
				"pozaumeze@gmail.com"
				);
		customers.add(poza);
		
		Customer mena = new Customer(
				2,
				"Wena",
				"Mena",
				"wenamena@gmail.com"
				);
		customers.add(mena);
	}

	@Override
	public List<Customer> selectAllCustomers() {
		// TODO Auto-generated method stub
		return customers;
	}

	@Override
	public Optional<Customer> selectCustomerById(Integer id) {
		return customers.stream()
				.filter(c -> c.getId().equals(id))
				.findFirst();
				
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customers.add(customer);
	}

	@Override
	public boolean existsPersonWithId(Integer id) {
		// TODO Auto-generated method stub
		return customers.stream()
				.anyMatch(c -> c.getId().equals(id));
	}

	@Override
	public void deleteCustomerById(Integer customerId) {
		// TODO Auto-generated method stub
		customers.stream()
		.filter(c -> c.getId().equals(customerId))
		.findFirst()
		.ifPresent(customers::remove);
	}

	@Override
	public void insertCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customers.add(customer);
	}

	@Override
	public boolean existsPersonWithEmail(String email) {
		// TODO Auto-generated method stub
		return customers.stream()
				.anyMatch(c -> c.getEmail().equals(email));
	}

}
