package com.study.domain.post;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;


    /*게시글 저장
    * param - PostRequest
    * return - id
    * */
    @Transactional
    public Long savePost(PostRequest params) {
        postMapper.save(params);
        return params.getId();
    }


    /*게시글 상세 조회
     * param - id
     * return - PostResponse
     * */
    public PostResponse findPostById(Long id) {
        return postMapper.findById(id);
    }


    /*게시글 수정
     * param - PostRequest
     * return - id
     * */
    @Transactional
    public Long updatePost(PostRequest params){
        postMapper.update(params);
        return params.getId();
    }


    /*게시글 삭제
     * param - id
     * return - id
     * */
    public Long deletePost(Long id) {
        postMapper.deleteById(id);
        return id;
    }


    /*게시글 목록 조회
     * param - PostRequest
     * return - id
     * */

    public List<PostResponse> findAllPost(){
        return postMapper.findAll();
    }
}
