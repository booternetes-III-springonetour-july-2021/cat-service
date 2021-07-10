/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * @author Madhura Bhave
 */
class CatsServiceTests {

	private CatsService service;

	private CatsRepository repository;

	@BeforeEach
	void setup() {
		this.repository = mock(CatsRepository.class);
		this.service = new CatsService(this.repository);
	}

	@Test
	void getCatShouldReturnCat() {
		given(this.repository.findByName(any())).willReturn(new Cat("Toby"));
		assertThat(this.service.getCat("Toby").getName()).isEqualTo("Toby");
	}
}
