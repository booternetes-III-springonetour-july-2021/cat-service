package com.example.demo;


import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class BaseClass {

    @MockBean
    private CatsRepository catsRepository;

    @Autowired
    private CatsService service;

    @BeforeEach
    public void before() {
        var cat = new Cat(1, "Toby");
        Mockito.when(this.catsRepository.findByName("Toby")).thenReturn(cat);
        RestAssuredMockMvc.standaloneSetup(new CatsRestController(this.service));
    }
}

