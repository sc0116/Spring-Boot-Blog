package com.example.blog.repository;

import com.example.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//자동으로 bean으로 등록됨
//@Repository 어노테이션 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {


}
