package com.musyimi.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {

	List<Customer> selectAllCustomers();
	Optional<Customer> selectCustomerById(Integer Id);
	void insertCustomer(Customer customer);
	boolean existsPersonWithEmail(String email);
}
 