package com.study.domain.comment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 같은 패키지의 클래스와 상속받은 클래스에서 생성자에 접근 가능
public class CommentRequest {

    private Long id;            // 댓글 번호
    private Long postId;        // 게시글 번호
    private String writer;      // 작성자
    private String content;     // 내용

}
