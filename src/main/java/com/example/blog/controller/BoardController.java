package com.example.blog.controller;

import com.example.blog.config.auth.PrincipalDetail;
import com.example.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    //컨트롤러에서 세션을 어떻게 찾는지?
    //@AuthenticationPrincipal PrincipalDetail principal
    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("boards", boardService.글목록());
        //resources/templates/index.html
        return "index"; //viewResolver 작동
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }
}
