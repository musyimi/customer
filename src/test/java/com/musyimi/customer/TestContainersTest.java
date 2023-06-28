package com.musyimi.customer;

import static org.assertj.core.api.Assertions.assertThat;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
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
	
	@DynamicPropertySource
	private static void registerDataSourceProperties(DynamicPropertyRegistry registry) {
		registry.add(
				"Spring.datasource.url",
				postgreSQLContainer::getJdbcUrl);
		registry.add(
				"Spring.datasource.username",
				postgreSQLContainer::getUsername);
		registry.add(
				"Spring.datasource.password",
				postgreSQLContainer::getPassword);
	}
	
	@Test
	void canStartPostgresDB() {
		assertThat(postgreSQLContainer.isRunning()).isTrue();
		assertThat(postgreSQLContainer.isCreated()).isTrue();
		
	}
	
	@Test
	void canApplyDbMigrationsWithFlyway() {
		Flyway flyway = Flyway.configure().dataSource(
				postgreSQLContainer.getJdbcUrl(),
				postgreSQLContainer.getUsername(),
				postgreSQLContainer.getPassword()
				).load();
		flyway.migrate();
	}

}
