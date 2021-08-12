package com.example.demo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;

@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    private static final String TOKEN_HEADER_KEY = "auth-token";

    private static final List<String> WHITELISTED_SWAGGER_URIS = asList(
            "/v2/api-docs",
            "/swagger-resources/**",
            "/configuration/security/**",
            "/swagger-ui.html");

    private final List<String> authKeys;

    AuthenticationFilter(List<String> authKeyConfig) {
        this.authKeys = extractAuthKeysFromConfig(authKeyConfig);
    }

    private boolean requestedUriIsWhitelisted(HttpServletRequest request) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        WHITELISTED_SWAGGER_URIS.stream()
                .anyMatch(prefix -> {
                  return  antPathMatcher.matchStart(prefix, request.getRequestURI());
                });
        return WHITELISTED_SWAGGER_URIS.stream()
                .anyMatch(prefix -> antPathMatcher.matchStart(prefix, request.getRequestURI()));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        if (requestedUriIsWhitelisted(request) || tokenIsValid(request)) {
            String authToken = request.getHeader(TOKEN_HEADER_KEY);
            log.info("hash:{}, url:{}",
                    (authToken != null) ? DigestUtils.sha256Hex(authToken) : "No token for this URI",
                    request.getRequestURI());
            filterChain.doFilter(request, response);
            return;
        }

        response.sendError(SC_FORBIDDEN, "[AUTH FILTER] Authentication Failed");
    }

    private boolean tokenIsValid(HttpServletRequest request) {
        String providedKey = request.getHeader(TOKEN_HEADER_KEY);
        return !StringUtils.isEmpty(providedKey) && authKeys.contains(providedKey);
    }

    private static List<String> extractAuthKeysFromConfig(List<String> authKeyConfig) {
        if (authKeyConfig == null) {
            return emptyList();
        }
        return authKeyConfig;
    }
}
