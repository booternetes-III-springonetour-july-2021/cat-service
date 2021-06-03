/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.demo;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.assertj.core.api.Assertions.assertThatNoException;

/**
 * @author Madhura Bhave
 */
class CatTests {

	@Test
	void createCatWithValidNameShouldSucceed() {
		assertThatNoException().isThrownBy(() -> new Cat("Toby"));
	}

	@Test
	void createCatWithShortNameShouldThrowException() {
		assertThatIllegalStateException().isThrownBy(() -> new Cat("tom"));
	}

	@Test
	void createCatWithLowercaseNameShouldThrowException() {
		assertThatIllegalArgumentException().isThrownBy(() -> new Cat("a"));
	}

	@Test
	void getNameShouldReturnName() {
		Cat cat = new Cat("Toby");
		assertThat(cat.getName()).isEqualTo("Toby");
	}

	@Test
	void getAgeShouldReturnAge() {
		Cat cat = new Cat("Toby", LocalDate.now().minusMonths(3));
		assertThat(cat.getAgeInMonths()).isEqualTo(3);
	}

	@Test
	void defaultAgeIsFourMonth() {
		Cat cat = new Cat("Paprika");
		assertThat(cat.getAgeInMonths()).isEqualTo(0);
	}
}
