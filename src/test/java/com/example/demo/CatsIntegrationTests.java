

package com.example.demo;

import org.hibernate.dialect.PostgreSQL9Dialect;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

/**
	* @author Madhura Bhave
	* @author Josh Long
	*/
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class CatsIntegrationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Container
	static PostgreSQLContainer<?> db = new PostgreSQLContainer<>("postgres");

	@DynamicPropertySource
	static void registerPostgresqlProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.jpa.database-platform", PostgreSQL9Dialect.class::getName);
		registry.add("spring.datasource.url", () -> db.getJdbcUrl());
		registry.add("spring.datasource.username", () -> db.getUsername());
		registry.add("spring.datasource.password", () -> db.getPassword());
	}

	@Test
	void getCatSucceeds() {
		Cat cat = this.testRestTemplate.getForObject("/cats/{name}", Cat.class, "Toby");
		assertThat(cat.getName()).isEqualTo("Toby");
	}
}
