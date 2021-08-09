package com.example.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob    //대용량 데이터
    private String content; //섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨(글자 용량 커짐)

    private int count;  //조회수

    @ManyToOne  //Many = Board, One = User
    @JoinColumn(name = "userId")
    private User user;  //DB는 오브젝트를 저장할 수 없기 때문에 FK사용, 자바는 오브젝트 저장할 수 있음

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)  //mappedBy 연관관계의 주인이 아님을 뜻함(FK가 아님) 그러므로 DB에 컬럼 만들지 않음
    @JsonIgnoreProperties({"board"})
    @OrderBy("id desc")
    private List<Reply> replyList;

    //@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}


