package com.example.demo;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
/*@TestPropertySource(locations = "classpath:test.properties")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@TestPropertySource(properties = {"ENVIRONMENT = test"})
@TestPropertySource(
        properties = {
                "ENVIRONMENT=test"
        },
        locations = {
                "classpath:test.properties"
        })*/
@TestPropertySource(locations = "classpath:test.properties")
public abstract class DatabaseIntegrationTest {
}
