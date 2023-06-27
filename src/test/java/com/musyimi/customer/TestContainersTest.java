package com.musyimi.customer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class TestContainersTest {
	
	@Container
	private static final PostgreSQLContainer<?> postgreSQLContainer = 
			new PostgreSQLContainer<>("postgres:latest")
			.withDatabaseName("customer-dao-unit-test")
			.withUsername("musyimi")
			.withPassword("password");
	
	@Test
	void canStartPostgresDB() {
		assertThat(postgreSQLContainer.isRunning()).isTrue();
		assertThat(postgreSQLContainer.isCreated()).isTrue();
		
	}

}
