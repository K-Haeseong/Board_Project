package com.study.domain.post;

import com.study.common.dto.MessageDto;
import com.study.common.dto.SearchDto;
import com.study.common.file.FileUtils;
import com.study.common.paging.PagingResponse;
import com.study.domain.file.FileRequest;
import com.study.domain.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final FileService fileService;
    private final FileUtils fileUtils;

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
        Long id = postService.savePost(params);                                 // 게시글 저장
        List<FileRequest> files = fileUtils.uploadFiles(params.getFiles());     // 디스크에 파일 업로드
        fileService.saveFiles(id, files);                                       // 업로드 된 파일 정보 DB에 저장
        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/post/list", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }


    // 게시글 리스트
    @GetMapping("/post/list")
    public String openPostList(@ModelAttribute("params") SearchDto params, Model model) {
        PagingResponse<PostResponse> response  = postService.findAllPost(params);
        model.addAttribute("response", response);
        return "post/list";

        // @ModelAttribute("params") 를 생략하면 안되는 이유가
        // 원래는 객체를 생성해서 파라미터로 값이 들어온 값을 바인딩 해주고 model에 넣어주는데
        // 여기서는 생성자를 통해 기본값을 할당 받기 때문에
        // 내가 직접 모델에 넣어주거나 @ModelAttribute가 자동으로 model에 넣어주게끔 해야 하는건가?
        //  -> 생략해도 잘 작동 해야 된다는데 부트 오류인듯
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
    public String updatePost(PostRequest params, SearchDto queryParams, Model model) {
        postService.updatePost(params);
        MessageDto message = new MessageDto("게시글 수정이 완료되었습니다.", "/post/list", RequestMethod.GET, queryParamsToMap(queryParams));
        return showMessageAndRedirect(message, model);
    }


    // 게시글 삭제 - 물리적 삭제 X / 논리적 삭제 O
    @PostMapping("/post/delete")
    public String deletePost(Long id, SearchDto queryParams, Model model) {
        postService.deletePost(id);
        MessageDto message = new MessageDto("게시글 삭제가 완료되었습니다.", "/post/list", RequestMethod.GET, queryParamsToMap(queryParams));
        return showMessageAndRedirect(message, model);
    }

    private Map<String, Object> queryParamsToMap(SearchDto queryParams) {
        Map<String, Object> data = new HashMap<>();
        data.put("page", queryParams.getPage());
        data.put("recordSize", queryParams.getRecordSize());
        data.put("pageSize", queryParams.getPageSize());
        data.put("keyword", queryParams.getKeyword());
        data.put("searchType", queryParams.getSearchType());
        return data;
    }


    // 사용자에게 메시지를 전달, 페이지 리다이렉트
    private String showMessageAndRedirect(MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
    }

}
