package com.example.demo;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles({ "default", "local", "test" })
@TestPropertySource(locations = "classpath:test.properties")
public abstract class BaseComponentTest {

    //This is for rest assured
    @LocalServerPort
    private int port;

    @Autowired
    protected MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        //This is for rest assured
        RestAssured.port = port;
    }

    @AfterAll
    public static void reset() {
        //This is for rest assured
        RestAssured.reset();
    }
}
