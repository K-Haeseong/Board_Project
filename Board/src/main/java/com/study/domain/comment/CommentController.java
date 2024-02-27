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
                                       @RequestBody CommentRequest params)  {

        Long id = commentService.saveComment(params);
        return commentService.findCommentById(id);

    }

    // 댓글 목록 조회
    @GetMapping("/posts/{postId}/comments")
    public List<CommentResponse> findAllComment(@PathVariable Long postId) {

        return commentService.findAllComment(postId);

    }

    // 댓글 상세정보 조회
    @GetMapping("/posts/{postId}/comments/{id}")
    public CommentResponse findCommentById(@PathVariable Long postId,
                                           @PathVariable Long id) {
        return commentService.findCommentById(id);
    }

    // 댓글 수정
    @PatchMapping("/posts/{postId}/comments/{id}")
    public CommentResponse updateComment(@PathVariable Long postId,
                                         @PathVariable Long id,
                                         @RequestBody CommentRequest params) {
        commentService.updateComment(params);
        return commentService.findCommentById(id);
    }

    // 댓글 삭제
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public Long deleteComment(@PathVariable Long postId,
                              @PathVariable Long id) {
        return commentService.deleteComment(id);
    }

}
