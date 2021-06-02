package com.example.demo;


import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BaseClass {

    @MockBean
    private CatsRepository catsRepository;

    @Autowired
    private CatsService service;

    @BeforeEach
    public void before() {
        System.out.println("before...");
        var cat = new Cat(1, "Toby");
        Mockito.when(this.catsRepository.findByName("Toby")).thenReturn(cat);
        RestAssuredMockMvc.standaloneSetup(new CatsRestController(this.service));
    }
}
