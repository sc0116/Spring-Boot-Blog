package com.example.blog.test;

import com.example.blog.model.Board;
import com.example.blog.model.RoleType;
import com.example.blog.model.User;
import com.example.blog.repository.BoardRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

//html파일이 아니라 data를 리턴해주는 controller = RestController
@RestController
public class DummyController {

    @Autowired  //의존성 주입 (DI)
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {

        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
        }

        return "삭제되었습니다. id: " + id;
    }

    //save함수는 id를 전달하지 않으면 insert함
    //save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update함
    //save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert함
    @Transactional  //함수 종료시에 자동 commit됨
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) {   //json데이터를 요청 => Java Object로 변환해서 받음

        System.out.println("id: " + id);
        System.out.println("password: " + requestUser.getPassword());
        System.out.println("email: " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(() -> {
           return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
        //userRepository.save(user);
        //더티 체킹
        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    //한 페이지당 2건의 데이터를 리턴받아 볼 예정
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return users;
    }

    //한 페이지당 2건의 데이터를 리턴받아 볼 예정
    @GetMapping("/dummy/board")
    public List<Board> pageBoardList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Board> pagingBoard = boardRepository.findAll(pageable);

        List<Board> boards = pagingBoard.getContent();
        return boards;
    }

    //{id} 주소로 파라미터를 전달 받을 수 있음
    //http://localhost:8080/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        //user/4를 DB에서 못찾아오게 되면 user가 null이 되는데,
        //그럼 return null이 될 때 프로그램에 문제가 있을 수 있음
        //Optional로 User객체를 감싸서 가져오면 null인지 아닌지 판단해서 return
//        User user = userRepository.findById(id).orElseThrow(() -> {
//            return new IllegalArgumentException("해당 유저는 없습니다. id: " + id);
//        });
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id: " + id);
            }
        });
        //요청: 웹브라우저
        //user 객체 = 자바 오브젝트
        //변환 (웹브라우저가 이해할 수 있는 데이터) -> json
        //스프링부트 = MessageConverter가 응답 시에 자동으로 작동
        //만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
        //user 오브젝트를 json으로 변환해서 브라우저에게 보냄
        return user;
    }

    //http://localhost:6109/blog/dummy/join (요청)
    //http의 body에 username, password, email 데이터를 가지고 (요청)
    @PostMapping("/dummy/join")
    public String join(User user) {
        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + user.getPassword());
        System.out.println("email: " + user.getEmail());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}
