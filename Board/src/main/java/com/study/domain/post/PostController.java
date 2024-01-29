package com.study.domain.post;

import com.study.common.dto.MessageDto;
import com.study.common.dto.SearchDto;
import com.study.common.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String savePost(PostRequest params, Model model) {
        postService.savePost(params);
        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/post/list", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }


    // 게시글 리스트
    @GetMapping("/post/list")
    public String openPostList(@ModelAttribute("params") SearchDto params, Model model) {
        PagingResponse<PostResponse> response  = postService.findAllPost(params);
        model.addAttribute("response", response);
        return "post/list";
    }


    // 게시글 상세 조회
    @GetMapping("/post/view")
    public String openPostView(Long id, Model model) {
//    public String openPostView(@RequestParam("id") Long id, Model model) {
        PostResponse post = postService.findPostById(id);
        model.addAttribute("post", post);
        return "post/view";
    }


    // 게시글 수정
    @PostMapping("/post/update")
    public String updatePost(PostRequest params, Model model) {
        postService.updatePost(params);
        MessageDto message = new MessageDto("게시글 수정이 완료되었습니다.", "/post/list", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }


    // 게시글 삭제 - 물리적 삭제 X / 논리적 삭제 O
    @PostMapping("/post/delete")
    public String deletePost(Long id, Model model) {
        postService.deletePost(id);
        MessageDto message = new MessageDto("게시글 삭제가 완료되었습니다.", "/post/list", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    
    // 사용자에게 메시지를 전달, 페이지 리다이렉트
    private String showMessageAndRedirect(MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
    }

}
