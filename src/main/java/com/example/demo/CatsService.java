/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.demo;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Madhura Bhave
 */
@Service
@Transactional
class CatsService {

	private final CatsRepository repository;

	CatsService(CatsRepository repository) {
		this.repository = repository;
	}

	public Cat getCat(String name) {
		return this.repository.findByName(name);
	}
}
