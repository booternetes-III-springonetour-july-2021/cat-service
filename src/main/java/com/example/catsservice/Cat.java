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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.util.Assert;

/**
 @author Madhura Bhave
 @author Josh Long
 **/
@Entity
@Table(name = "cat")
class Cat {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private Cat() {
	}

	public Cat(String name) {
		Assert.isTrue(Character.isUpperCase(name.charAt(0)),
				() -> "the name must start with a capital letter");
		this.name = name;

	}

	public String getName() {
		return this.name;
	}
}
