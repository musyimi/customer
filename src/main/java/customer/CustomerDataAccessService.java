package customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class CustomerDataAccessService implements CustomerDao {
	

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

}
