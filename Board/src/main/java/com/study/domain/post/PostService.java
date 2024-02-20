package com.study.domain.post;

import com.study.common.dto.SearchDto;
import com.study.common.paging.Pagination;
import com.study.common.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;


    /* 게시글 저장
     * param - PostRequest
     * return - id
     * */
    @Transactional
    public Long savePost(PostRequest params) {
        postMapper.save(params);
        return params.getId();
    }


    /* 게시글 상세 조회
     * param - id
     * return - PostResponse
     * */
    public PostResponse findPostById(Long id) {
        return postMapper.findById(id);
    }


    /* 게시글 수정
     * param - PostRequest
     * return - id
     * */
    @Transactional
    public Long updatePost(PostRequest params){
        postMapper.update(params);
        return params.getId();
    }


    /* 게시글 삭제
     * param - id
     * return - id
     * */
    public Long deletePost(Long id) {
        postMapper.deleteById(id);
        return id;
    }


    /* 게시글 목록 조회
     * param - SearchDto
     * return - list & pagination information
     * */

    public PagingResponse<PostResponse> findAllPost(SearchDto params){

        // 조건에 해당하는 데이터가 없으면, 응답 데이터에 빈 리스트 & null 반환
        int count = postMapper.count(params);
        if(count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        // Pagination 객체에 페이지 정보 계산 후 SearchDto에 페이지 정보 저장
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        // limitStart, recordSize를 기준으로 리스트 조회
        List<PostResponse> list = postMapper.findAll(params);
        return new PagingResponse<>(list, pagination);
    }
}
