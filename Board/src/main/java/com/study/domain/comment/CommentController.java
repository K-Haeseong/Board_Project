package com.study.domain.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 신규 댓글 생성
    @PostMapping("/post/{postId}/comments")
    public CommentResponse saveComment(@PathVariable Long postId,
                                       @RequestBody CommentRequest params) {

        Long id = commentService.saveComment(params);
        return commentService.findCommentById(id);

    }

}
