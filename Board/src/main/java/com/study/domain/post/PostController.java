package com.study.domain.post;

import lombok.RequiredArgsConstructor;
import lombok.experimental.PackagePrivate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @GetMapping("/post/write")
    public String openPostWrite(@RequestParam(value = "id", required = false)Long id, Model model) {

        if(id != null) {
            PostResponse post = postService.findPostById(id);
            model.addAttribute("post", post);
        }
        return "post/write";
    }

    // 신규 게시글 생성
    @PostMapping("/post/save")
    public String savePost(PostRequest params) {
        postService.savePost(params);
        return "redirect:/post/list";
    }


    @GetMapping("/post/list")
    public String openPostList(Model model) {
        List<PostResponse> posts = postService.findAllPost();
        model.addAttribute("posts", posts);
        return "post/list";

    }

}
