package com.musyimi.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.musyimi.exception.DuplicateResourceException;
import com.musyimi.exception.RequestValidationException;
import com.musyimi.exception.ResourceNotFoundException;

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
		customerDao.insertCustomer(
				new Customer(
						customerRegistrationRequest.first_name(),
						customerRegistrationRequest.last_name(),
						customerRegistrationRequest.email()
						
						)
				);
				}
	
	public void deleteCustomerById(Integer customerId) {
		if(!customerDao.existsPersonWithId(customerId)) {
			throw new ResourceNotFoundException(
					"customer with id not found".formatted(customerId)
					);
		}
		customerDao.deleteCustomerById(customerId);
	}
	
	public void updateCustomer(Integer customerId,
			CustomerUpdateRequest updateRequest) {
		Customer customer = getCustomer(customerId);
		
		boolean changes = false;
		
		if (updateRequest.first_name() != null && !updateRequest.first_name().equals(customer.getFirst_name())) {
			 customer.setFirst_name(updateRequest.first_name());
			 changes = true;
		}
		  
		
		if (updateRequest.last_name() != null && !updateRequest.last_name().equals(customer.getLast_name())) {
			 customer.setLast_name(updateRequest.last_name());
			 changes = true;
		}
		
		if (updateRequest.email() != null && !updateRequest.email().equals(customer.getEmail())) {
			if (customerDao.existsPersonWithEmail(updateRequest.email())) {
				throw new DuplicateResourceException(
						"email already taken"
						);
			}
			
			customer.setEmail(updateRequest.email());
			 changes = true;
		}
		
		if (!changes) {
			throw new RequestValidationException("No data changes found");
		}
		
		customerDao.updateCustomer(customer);
	}
	

}
