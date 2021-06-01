

package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Madhura Bhave
 */
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "spring.jpa.hibernate.ddl-auto=none")
@AutoConfigureTestDatabase
public class CatsIntegrationTests {
    //https://blog.codeleak.pl/2020/03/spring-boot-tests-with-testcontainers.html
//spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver
//spring.datasource.url=jdbc:tc:postgresql:9.6:///
//spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL9Dialect

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Container
    static PostgreSQLContainer<?> db = new PostgreSQLContainer<>("postgres");

    @DynamicPropertySource
    static void neo4jProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", CatsIntegrationTests::get);
    }

    private static Object get() {
        return db.getJdbcUrl();
    }


    @Test
    void getCatSucceeds() {
        Cat cat = this.testRestTemplate.getForObject("/cats/{name}", Cat.class, "Toby");
        assertThat(cat.getName()).isEqualTo("Toby");
    }
}
