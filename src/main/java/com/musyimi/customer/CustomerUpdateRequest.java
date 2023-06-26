package com.musyimi.customer;

public record CustomerUpdateRequest(
		   String first_name,
		   String last_name,
		   String email
		) {
   
}
