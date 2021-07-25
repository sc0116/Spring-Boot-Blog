package com.example.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogController {

    @GetMapping("/test/hello")
    public String hello() {
        System.out.println("hello spring boot");
        return "<h1>hello spring boot</h1>";
    }
}
