package com.study.domain.comment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommentMapperTest {

    @Autowired
    CommentService commentService;

    // 댓글 생성용
   /* @Test
    void save() {
        for (long i = 1; i<=1000; i++) {
            CommentRequest params = new CommentRequest();
            params.setId(i);
            params.setPostId(1000L);
            params.setWriter(i + "번 테스터");
            params.setContent(i + "번 댓글");
            commentService.saveComment(params);
        }
    }*/
}