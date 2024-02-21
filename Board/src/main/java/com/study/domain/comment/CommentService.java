package com.study.domain.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;


    /* 댓글 저장 */
    public Long saveComment(CommentRequest params) {
        commentMapper.save(params);
        return params.getId();
        // id를 왜 반환하는지 생각해보기
    }

    /* 댓글 상세 조회 */
    public CommentResponse findCommentById(Long id) {
        return commentMapper.findById(id);
    }

    /* 댓글 수정 */
    public Long updateComment(CommentRequest params) {
        commentMapper.update(params);
        return params.getId();
        // id를 왜 반환하는지 생각해보기
    }

    /* 댓글 삭제 */
    public Long deleteComment(Long id) {
        commentMapper.deleteById(id);
        return id;
        // id를 왜 반환하는지 생각해보기
    }

    /* 댓글 목록 조회 */
    public List<CommentResponse> findAllComment(Long postId) {
        return commentMapper.findAll(postId);
    }

}
