/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.catservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.util.Assert;

/**
 * @author Madhura Bhave
 */
@Entity
@Table(name = "cat")
public class Cat {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Cat() {
    }

    public Cat(String name) {
         Assert.isTrue(Character.isUpperCase(name.charAt(0)), () -> "the name should start with an uppercase!");
         this.name = name;
    }

    public String getName() {
        return name;
    }
}
