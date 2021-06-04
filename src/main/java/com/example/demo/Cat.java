/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.demo;

import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.Period;

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

     /* for testing */
     Cat(Integer id, String name) {
        Assert.state(id != null && id > 0, () -> "the id should not be null");
        this.id = id;
        init(name, LocalDate.now());
    }

    public Cat(String name) {
        init(name, LocalDate.now());
    }

    public Cat(String name, LocalDate dateOfBirth) {
        init(name, dateOfBirth);
    }

    private void init(String name, LocalDate dateOfBirth) {
        Assert.isTrue(name.length() > 1, () -> "the name should have more than one character ");
        Assert.state(Character.isUpperCase(name.charAt(0)), () -> "the name should start with an uppercase!");
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public int getAgeInMonths() {
        return Period.between(dateOfBirth, LocalDate.now()).getMonths();
    }
}
