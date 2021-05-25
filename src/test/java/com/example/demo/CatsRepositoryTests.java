/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.demo;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Madhura Bhave
 */
@DataJpaTest
public class CatsRepositoryTests {

	private final CatsRepository repository;

	@Autowired
	public CatsRepositoryTests(CatsRepository repository) {
		this.repository = repository;
	}

	@Test
	void findByNameShouldReturnName() {
		this.repository.save(new Cat("Toby"));
		assertThat(this.repository.findByName("Toby")).isNotNull();
	}
}
