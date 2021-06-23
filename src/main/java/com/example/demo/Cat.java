/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.demo;

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

    private Integer ageInMonths;

    private Cat() {
    }

     /* for testing */
     Cat(Integer id, String name) {
        Assert.state(id != null && id > 0, () -> "the id should not be null");
        this.id = id;
         init(name, 4);
    }

    public Cat(String name) {
        init(name, 4);
    }

    public Cat(String name, int ageInMonths) {
        init(name, ageInMonths);
    }

    private void init(String name, int ageInMonths) {
        Assert.isTrue(name.length() > 1, () -> "the name should have more than one character ");
        Assert.state(Character.isUpperCase(name.charAt(0)), () -> "the name should start with an uppercase!");
        this.name = name;
        this.ageInMonths = ageInMonths;
    }

    public String getName() {
        return name;
    }

    public int getAgeInMonths() {
        return ageInMonths;
    }
}
