package com.study.domain.comment;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    /* 댓글 저장 */
    void save(CommentRequest params);

    /* 댓글 상세정보 조회 */
    CommentResponse findById(Long id);

    /* 댓글 수정 */
    void update(CommentRequest params);

    /* 댓글 삭제 */
    void deleteById(Long id);

    /* 댓글 목록 조회 */
    List<CommentResponse> findAll(Long postId);

    /* 댓글 수 계산 */
    int count(Long postId);

}
