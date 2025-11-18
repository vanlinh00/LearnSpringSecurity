package org.example.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
}