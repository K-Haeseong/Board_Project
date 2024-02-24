package com.study.domain.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 신규 댓글 생성
    @PostMapping("/posts/{postId}/comments")
    public CommentResponse saveComment(@PathVariable Long postId,
                                       @RequestBody CommentRequest params) {

        Long id = commentService.saveComment(params);
        return commentService.findCommentById(id);

    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentResponse> findAllComment(@PathVariable Long postId) {

        return commentService.findAllComment(postId);

    }






}
