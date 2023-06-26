package com.musyimi.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository("jpa")
public class CustomerJPADataAccessService implements CustomerDao {
	
	private final CustomerRepository customerRepository;

	public CustomerJPADataAccessService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> selectAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public Optional<Customer> selectCustomerById(Integer Id) {
		// TODO Auto-generated method stub
		return customerRepository.findById(Id);
	}

	@Override
	public void insertCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepository.save(customer);
		
	}

	@Override
	public boolean existsPersonWithId(Integer id) {
		// TODO Auto-generated method stub
		return customerRepository.existsCustomerById(id);
	}

	@Override
	public void deleteCustomerById(Integer customerId) {
		// TODO Auto-generated method stub
		customerRepository.deleteById(customerId);
	}

	@Override
	public boolean existsPersonWithEmail(String email) {
		// TODO Auto-generated method stub
		return customerRepository.existsCustomerByEmail(email);
	}

}
