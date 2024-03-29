package com.study;


import com.study.domain.post.PostRequest;
import com.study.domain.post.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostMapperTest {

    @Autowired
    PostService postService;


//    @Autowired
//    PostMapper postMapper;
//
//    @Test
//    void save() {
//        PostRequest params = new PostRequest();
//        params.setTitle("4번 게시글 제목");
//        params.setContent("4번 게시글 내용");
//        params.setWriter("테스터");
//        params.setNoticeYn(false);
//        postMapper.save(params);
//
//        List<PostResponse> posts = postMapper.findAll();
//        System.out.println("전체 게시글 개수는 : " + posts.size() + "개입니다.");
//    }
//
//
//    @Test
//    void findById() {
//        PostResponse post = postMapper.findById(7L);
//        try {
//            String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
//            System.out.println(postJson);
//
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    void update() {
//        // 1. 게시글 수정
//        PostRequest params = new PostRequest();
//        params.setId(7L);
//        params.setTitle("1번 게시글 제목 수정합니다.");
//        params.setContent("1번 게시글 내용 수정합니다.");
//        params.setWriter("회원1");
//        params.setNoticeYn(true);
//        postMapper.update(params);
//
//        // 2. 게시글 상세정보 조회
//        PostResponse post = postMapper.findById(7L);
//        try {
//            String postJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(post);
//            System.out.println(postJson);
//
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @Test
//    void delete() {
//        System.out.println("삭제 이전의 전체 게시글 개수는 : " + postMapper.findAll().size() + "개입니다.");
//        postMapper.deleteById(7L);
//        System.out.println("삭제 이후의 전체 게시글 개수는 : " + postMapper.findAll().size() + "개입니다.");
//    }

    @Test
    void saveByForeach(){
        for (int i = 1; i<=1000; i++) {
            PostRequest params = new PostRequest();
            params.setTitle(i + "번 게시글 제목");
            params.setContent(i + "번 게시글 내용");
            params.setWriter("테스터" + i);
            params.setNoticeYn(false);
            postService.savePost(params);
        }
    }

}

