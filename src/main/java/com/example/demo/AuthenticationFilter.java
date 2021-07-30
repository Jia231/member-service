//package com.example.demo;
//
//import lombok.AllArgsConstructor;
//import org.springframework.util.AntPathMatcher;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//import java.util.stream.Stream;
//
//import static java.util.Arrays.asList;
//import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
//
//@AllArgsConstructor
//public class AuthenticationFilter extends OncePerRequestFilter {
//
//    private static final List<String> ALLOWED_SWAGGER_URIS = asList(
//            "/v2/api-docs",
//            "/configuration/ui/**",
//            "/swagger-resources/**",
//            "/configuration/security/**",
//            "/swagger-ui.html",
//            "/webjars/**");
//
//    private static final List<String> ALLOWED_APPLICATION_URIS = asList(
//            "/",
//            "/metrics",
//            "/healthCheck",
//            "/login",
//            "/saml/**");
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                    FilterChain filterChain)
//            throws IOException, ServletException {
//
//        if (requestedUriIsWhitelisted(request)
//                || isAnOptionsRequest(request)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        response.sendError(SC_UNAUTHORIZED, "[AUTH FILTER] Authentication Failed");
//    }
//
//    private boolean isAnOptionsRequest(HttpServletRequest request) {
//        return request.getMethod().equals("OPTIONS");
//    }
//
//    private boolean requestedUriIsWhitelisted(HttpServletRequest request) {
//        AntPathMatcher antPathMatcher = new AntPathMatcher();
//        return Stream.concat(ALLOWED_APPLICATION_URIS.stream(), ALLOWED_SWAGGER_URIS.stream())
//                .anyMatch(prefix -> antPathMatcher.matchStart(prefix, request.getRequestURI()));
//    }
//
//}
