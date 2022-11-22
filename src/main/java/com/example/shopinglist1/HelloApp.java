package com.example.shopinglist1;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApp {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
