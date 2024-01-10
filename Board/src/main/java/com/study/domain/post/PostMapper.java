package com.study.domain.post;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    /**
     * 게시글 저장
     * params - PostRequest(게시글 정보)
     */
    void save(PostRequest params);


    /**
     * 게시글 상세 조회
     * params - id
     * return - PostResponse(게시글 상세 정보)
     */
    PostResponse findById(Long id);


    /**
     * 게시글 수정
     * params - PostRequest(게시글 정보)
     */
    void update(PostRequest params);


    /**
     * 게시글 삭제
     * params - id
     */
    void deleteById(Long id);


    /**
     * 게시글 목록 조회
     * return - 게시글 리스트
     */
    List<PostResponse> findAll();


    /**
     * 게시글 수
     */
    int count();

}

