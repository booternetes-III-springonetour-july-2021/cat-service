

package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Madhura Bhave
 */
//
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = "spring.jpa.hibernate.ddl-auto=none")
@AutoConfigureTestDatabase
public class CatsIntegrationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getCatSucceeds() {
        Cat cat = this.testRestTemplate.getForObject("/cats/{name}", Cat.class, "Toby");
        assertThat(cat.getName()).isEqualTo("Toby");
    }
}
