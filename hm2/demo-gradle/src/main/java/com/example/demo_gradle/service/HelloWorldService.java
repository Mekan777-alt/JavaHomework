package com.example.demo_gradle.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldService {

    public HelloWorldService(
            @Value("${name:World}") String name) {
        this.name = name;
    }

    private final String name;

    public String getHelloMessage() {
        return "Hello " + this.name;
    }

}