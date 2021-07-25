package com.example.blog.model;


import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

//ORM -> Java Object -> 테이블로 매핑해주는 기술
@Entity //User 클래스가 MySQL에 테이블이 생성됨
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라감
    private int id; //Oracle: sequence, MySQL: auto_increment

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 100) //123456 => 해쉬(비밀번호 암호화)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("'user'")
    private String role;    //Enum을 쓰는게 좋음

    @CreationTimestamp  //시간이 자동 입력됨
    private Timestamp createdAt;
}
