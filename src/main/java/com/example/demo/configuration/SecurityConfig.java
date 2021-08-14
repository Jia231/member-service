package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Order(10)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${should.bypass.for.test}")
    private Boolean bypassForTest;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        if (bypassForTest) {
            return;
        }

        http.authorizeRequests()
                .antMatchers("/v2/api-docs",
                        "/swagger-resources/**",
                        "/swagger-ui.html")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login();
    }
}
