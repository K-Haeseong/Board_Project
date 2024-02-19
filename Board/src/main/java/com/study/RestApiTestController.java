package com.study;

import com.study.common.dto.SearchDto;
import com.study.common.paging.PagingResponse;
import com.study.domain.post.PostResponse;
import com.study.domain.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RestApiTestController {

    private final PostService postService;

    @GetMapping("/posts")
    public PagingResponse<PostResponse> findAllPost() {
        return postService.findAllPost( new SearchDto());
    }



    @GetMapping("/members")
    public List<Map<String, Object>> findAllMember() {

        List<Map<String, Object>> members = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {

            Map<String, Object> member = new HashMap<>();
            member.put("id", i);
            member.put("name", i + "번 작성자");
            member.put("age", 20 + i);
            members.add(member);
        }

        return members;
    }
}