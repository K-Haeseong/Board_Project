package com.study.domain.comment;

import com.study.common.paging.PagingResponse;
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
        // 컨트롤러에서 CommentResponse로 반환하는 이유는
        // 단순히 Long으로 반환하게 되면 클라이언트는 JSON을 받는 것이 아니라 HTTP Body에 단순히 숫자 하나를 받게 된다.
        // CommentResponse로 반환 -> { id: 1, postId: 1234, content: "첫번째 댓글" ..}
        // Long으로 반환 -> 10
        // 클라이언트와 서버간에 약속을 하는 것이기 때문에 단순히 Long으로 반환해도 상관은 없지만,
        // HTTP API에서는 데이터 응답을 JSON으로 받는다고 서로 약속을 하기 때문에 JSON으로 내려주는 것이 좋다고 한다.
    }


    // 댓글 목록 조회
    @GetMapping("/posts/{postId}/comments")
    public PagingResponse<CommentResponse> findAllComment(@PathVariable Long postId,
                                         @ModelAttribute CommentSearchDto params) {
        return commentService.findAllComment(params);
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
