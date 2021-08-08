package com.example.blog.test;

import com.example.blog.model.Board;
import com.example.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReplyControllerTest {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/test/board/{id}")
    public Board getBoard(@PathVariable int id) {
        return boardRepository.findById(id).get();
    }
}
