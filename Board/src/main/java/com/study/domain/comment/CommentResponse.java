package com.study.domain.comment;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {

    private Long id;                        // 댓글 번호
    private Long postId;                    // 게시글 번호
    private String writer;                  // 작성자
    private String content;                 // 내용
    private Boolean deleteYn;               // 삭제 여부
    private LocalDateTime createdDate;      // 생성일시
    private LocalDateTime modifiedDate;     // 최종 수정일시

}
