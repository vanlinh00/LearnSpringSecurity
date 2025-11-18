package org.example.controllers;

import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@RestController
public class DemoController {

    @GetMapping("/hello")
    public Map<String, String> hello(Authentication authentication) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello " + authentication.getName() + "!");
        return response;
    }

    @GetMapping("/")
    public Map<String, String> home() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Welcome to the API. Try /hello endpoint with authentication.");
        return response;
    }
    @GetMapping("/login")
    public Map<String, String> login() {

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication =
                new TestingAuthenticationToken("linhnv03", "password", "ROLE_USER");
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);

        Map<String, String> response = new HashMap<>();
        response.put("message", authentication.getName().toLowerCase(Locale.ROOT));
        return response;
    }
}