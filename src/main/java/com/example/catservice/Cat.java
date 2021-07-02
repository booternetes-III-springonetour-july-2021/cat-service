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
import java.time.LocalDate;
import java.time.Period;

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

    private LocalDate dateOfBirth;

    private Cat() {
    }

    public Cat(String name) {
        this(name, LocalDate.now().minusMonths(4));
    }

    public Cat(String name, LocalDate dateOfBirth) {
        Assert.isTrue(Character.isUpperCase(name.charAt(0)), () -> "the name should start with an uppercase!");
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAgeInMonths() {
        return Period.between(dateOfBirth, LocalDate.now()).getMonths();
    }

    public String getName() {
        return name;
    }
}
