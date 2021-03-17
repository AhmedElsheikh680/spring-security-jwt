package com.spring.security.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ApiSystem {

    @GetMapping("/myAdmin")
    public String myAdmin(){
        return "I'm Admin";
    }

    @GetMapping("/myAdminOrManager")
    public String myAdminOrManager(){
        return "I'm Admin OR Manager";
    }

    @GetMapping("/myAdminOrManagerOrUser")
    public String myAdminOrManagerOrUser(){
        return "I'm Admin OR Manager OR User";
    }
}
