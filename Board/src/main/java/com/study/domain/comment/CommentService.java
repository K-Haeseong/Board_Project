package com.study.domain.comment;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.study.common.paging.Pagination;
import com.study.common.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.expression.Lists;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;


    /* 댓글 저장 */
    @Transactional
    public Long saveComment(CommentRequest params) {
        commentMapper.save(params);
        return params.getId();
        // id를 왜 반환하는지 생각해보기
        // json을 통해 받을 response를 조회해보기 위해서 
        // id를 넘기고 넘어온 id를 통해서 상세 정보 조회 기능을 실행하면 
        // 저장한 동시에 console로 저장된 response 객체를 확인 할 수 있기 때문이다.
        // 따라서, id를 반환하는 이유는 commentResponse객체를 넘기기 위해서이다.
        // 서비스에서 id가 반환되어야 컨트롤러에서 id로 댓글 상세 조회를 통해 클라이언트에게 commentResponse객체를 넘길 수 있기 때문이다.
    }

    /* 댓글 상세 조회 */
    public CommentResponse findCommentById(Long id) {
        return commentMapper.findById(id);
    }

    /* 댓글 수정 */
    @Transactional
    public Long updateComment(CommentRequest params) {
        commentMapper.update(params);
        return params.getId();
    }

    /* 댓글 삭제 */
    @Transactional
    public Long deleteComment(Long id) {
        commentMapper.deleteById(id);
        return id;
        // id를 왜 반환하는지 생각해보기
    }

    /* 댓글 목록 조회 */
    public PagingResponse<CommentResponse> findAllComment(CommentSearchDto params) {

        int totalCount = commentMapper.count(params);

        if( totalCount < 1 ) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        Pagination pagination = new Pagination(totalCount, params);
        // params.setPagination(pagination);
        //  -> Pagination 생성자에서 처리
        List<CommentResponse> list = commentMapper.findAll(params);
        return new PagingResponse<>(list, pagination);
    }

}
