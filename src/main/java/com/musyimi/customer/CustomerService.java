package com.musyimi.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.musyimi.exception.DuplicateResourceException;

@Service
public class CustomerService {
	
	private final CustomerDao customerDao;
	
	public CustomerService(@Qualifier("jpa") CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public List<Customer> getAllCustomers() {
	  return customerDao.selectAllCustomers();
	  
	}
	
	public Customer getCustomer(Integer id) {
		  return customerDao.selectCustomerById(id)
				  .orElseThrow(
							() -> new IllegalArgumentException("customer with id [%s] not found".formatted(id)));
					
		  
		}
	
	public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
		String email = customerRegistrationRequest.email();
		if (customerDao.existsPersonWithEmail(email)) {
			throw new DuplicateResourceException("email already exists");
		}
				}
	

}
