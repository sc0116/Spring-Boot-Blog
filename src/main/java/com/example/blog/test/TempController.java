package com.example.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {

    @GetMapping("/temp/home")
    public String tempHome() {
        System.out.println("tempHome()");
        //파일리턴 기본경로: src/main/resources/static
        //리턴명: /index.html
        //풀경로: src/main/resources/static/index.html
        return "home";
    }
}
