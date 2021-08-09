package com.example.blog.repository;

import com.example.blog.dto.ReplySaveRequestDto;
import com.example.blog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    @Modifying
    @Query(value = "insert into reply(userId, boardId, content, createdAt) values(?1, ?2, ?3, now())", nativeQuery = true)
    int mSave(int userId, int boardId, String content); //업데이트된 행의 개수 리턴해줌
}
