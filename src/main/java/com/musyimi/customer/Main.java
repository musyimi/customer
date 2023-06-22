package com.musyimi.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Main {
	
	// db
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

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
	
	@GetMapping("api/v1/customers")
	public List<Customer> getCustomers() {
		return customers;
	}
	
	@GetMapping("api/v1/customers/{customerId}")
	public Customer getCustomer(
			@PathVariable("customerId") Integer customerId
			) {		
		Customer customer =  customers.stream()
				.filter(c -> c.id.equals(customerId))
				.findFirst()
				.orElseThrow(
						() -> new IllegalArgumentException("customer with id [%s] not found".formatted(customerId)));
				
				return customer;
	}
	
	 static class Customer{
		private Integer id;
		private String first_name;
		private String last_name;
		private String email;
	
		
		public Customer() {

		}

		public Customer(Integer id, String first_name, String last_name, String email) {
			this.id = id;
			this.first_name = first_name;
			this.last_name = last_name;
			this.email = email;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getFirst_name() {
			return first_name;
		}

		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}

		public String getLast_name() {
			return last_name;
		}

		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + Objects.hash(email, first_name, id, last_name);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Customer other = (Customer) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			return Objects.equals(email, other.email) && Objects.equals(first_name, other.first_name)
					&& Objects.equals(id, other.id) && Objects.equals(last_name, other.last_name);
		}

		private Main getEnclosingInstance() {
			return Main.this;
		}

		@Override
		public String toString() {
			return "Customer [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
					+ "]";
		}

		
	}
		

}
