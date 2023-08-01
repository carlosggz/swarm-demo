package com.example.swarmdemo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Value("${app.id}")
    private String id;

    @GetMapping
    public String getValue(){
        return "Response from " + id;
    }
}
