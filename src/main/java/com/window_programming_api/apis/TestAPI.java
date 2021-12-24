package com.window_programming_api.apis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAPI {
    @GetMapping("/test")
    public String getStudent() {
        return "hello";
    }
}
