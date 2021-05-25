/*
 * Copyright 2012-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.catsservice;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import org.assertj.core.api.Assertions;
import org.hibernate.dialect.PostgreSQL9Dialect;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

/**
 @author Madhura Bhave
 @author Josh Long
 **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class CatIntegrationTests {

	@Container
	static PostgreSQLContainer<?> db = new PostgreSQLContainer<>("postgres");

	@DynamicPropertySource
	static void propertySource(DynamicPropertyRegistry registry) {
		registry.add("spring.jpa.database-platform", PostgreSQL9Dialect.class::getName);
		registry.add("spring.datasource.url", () -> db.getJdbcUrl());
		registry.add("spring.datasource.username", () -> db.getUsername());
		registry.add("spring.datasource.password", () -> db.getPassword());
	}

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private DataSource ds;

	@Autowired
	private JdbcTemplate template;

	@Test
	void getName() {
		System.out.println(this.ds.toString());

		Cat cat = this.testRestTemplate.getForObject("/cats/{name}", Cat.class, "Toby");
		assertThat(cat.getName()).isEqualTo("Toby");
	}
}
