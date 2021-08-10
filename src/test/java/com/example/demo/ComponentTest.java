package com.example.demo;

import io.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@Component
@TestPropertySource(locations = "classpath:test.properties")
public abstract class ComponentTest {
    @LocalServerPort
    public int port;

    @Before
    public void setup() {
        RestAssured.port = port;
    }

    @AfterClass
    public static void reset() {
        RestAssured.reset();
    }
}
