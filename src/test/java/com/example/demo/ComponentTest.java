package com.example.demo;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@Component
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public abstract class ComponentTest {
    @LocalServerPort
    public int port;

    public static Header aValidAuthHeader() {
        return new Header("auth-token", "aValidToken");
    }

    static {
        System.setProperty("http.keepAlive", "false");
        System.setProperty("http.maxConnections", "1");
    }


    @Before
    public void setupComponentTest() {
        RestAssured.port = port;
    }

    @AfterClass
    public static void reset() {
        RestAssured.reset();
    }
}
