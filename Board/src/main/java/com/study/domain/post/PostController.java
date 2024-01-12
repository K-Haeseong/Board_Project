package com.study.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post/write")
    public String openPostWrite(Model model){

        String title = "제목";
        String content = "내용";
        String writer = "테스터";

        model.addAttribute("t", title);
        model.addAttribute("c", content);
        model.addAttribute("w", writer);

        return "post/write";
    }
}
