package com.example.blog.test;

import org.springframework.web.bind.annotation.*;

//@Controller   //사용자가 요청 -> 응답(HTML 파일)
@RestController //사용자가 요청 -> 응답(Data)
public class HttpControllerTest {

    //인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다.
    //http://localhost:포트번호/http/get (select)
    @GetMapping("/http/get")
    public String getTest() {
        return "get 요청";
    }

    //http://localhost:포트번호/http/post (insert)
    @PostMapping("/http/post")
    public String postTest() {
        return "post 요청";
    }

    //http://localhost:포트번호/http/put (update)
    @PutMapping("/http/put")
    public String putTest() {
        return "put 요청";
    }

    //http://localhost:포트번호/http/delete (delete)
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }
}
