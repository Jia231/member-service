package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class DemoApplication {
    @Value("${spring.datasource.url}")
    private String dataSource;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public void showContext() {
        log.info("<<<My Datasource>>>: " + dataSource);
        log.info("<<<My Datasource password>>>: " + password);
        log.info("<<<My Datasource username>>>: " + username);
    }

}
