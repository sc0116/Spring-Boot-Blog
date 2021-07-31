package com.example.blog.repository;

import com.example.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//자동으로 bean으로 등록됨
//@Repository 어노테이션 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {

    //JPA Naming 전략
    //SELECT * FROM user WHERE username - ? AND password = ?;
    User findByUsernameAndPassword(String username, String password);

    //위에 한줄과 아래 두줄이 동일
    //@Query(value="SELECT * FROM user WHERE username = ? AND password = ?", nativeQuery=true)
    //User login(String username, String password);
}
