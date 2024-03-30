package com.study.domain.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    // final로 선언된 필드는 반드시 객체 생성 시점(생성자) 또는 선언 시점에서 초기화되어야 한다.
    // 초기화를 강제하고, 객체의 유효한 상태를 보장하는데 도움이 된다.


    // 로그인 페이지
    @GetMapping("/login")
    public String openLogin() {
        return "member/login";
    }


    // 로그인
    @PostMapping("/login")
    @ResponseBody
    public MemberResponse login(HttpServletRequest request) {

        // 회원 정보 조회
        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");
        MemberResponse member = memberService.login(loginId, password);

        // 세션에 회원 정보 저장 & 세션 유지 시간 설정
        if (member != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginMember", member);
            session.setMaxInactiveInterval(60 * 30);
        }

        return member;
    }

    // 로그아웃
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }


    @PostMapping("/members")
    @ResponseBody
    /* 회원 정보 저장(회원가입)*/
    public ResponseEntity<Long> memberSave(@RequestBody MemberRequest params) {

        Long id = memberService.saveMember(params);

        return ResponseEntity
                .ok()
                .body(id);
    }

    @GetMapping("/members/{loginId}")
    /* 회원 상세 정보 조회 */
    public ResponseEntity<MemberResponse> findMemberByLoginId(@PathVariable String loginId) {
        MemberResponse response = memberService.findMemberByLoginId(loginId);

        return ResponseEntity
                .ok()
                .body(response);
    // loginId로 구분 하는 이유
    //
    // 개인화 및 식별성: 로그인 아이디나 사용자명을 사용하여 회원을 식별하는 것은 해당 회원의 개인화된 정보를 제공하고, 다른 사용자와의 혼동을 방지하는 데 도움된다.
    //
    // 유지보수성: 만약 데이터베이스 스키마가 변경되어 PK가 바뀌거나 다른 식별자로 변경되더라도,
    //           로그인 아이디와 같은 비즈니스적으로 중요한 정보를 기준으로 조회하면 해당 변경에 영향을 덜 받을 수 있다.
    //
    // 보안: PK는 주로 데이터베이스 내부에서 사용되는 값이며, 외부에 노출되는 경우가 제한적이다.
    //      반면 로그인 아이디나 사용자명은 사용자에게 제공되는 정보이므로 보다 안전하게 활용될 수 있다.
    //      자세한건 <회원아이디를 URL의 식별자로 쓰는 이유 / PK 노출 위험성>정리본 참고
    }

    /* 회원 수 세기(중복체크) */
    @GetMapping("/member-count")
    @ResponseBody
    public ResponseEntity<Integer> countMemberByLoginId(@RequestParam("loginId") String loginId) {
        int count = memberService.countMemberByLoginId(loginId);
        return ResponseEntity
                .ok()
                .body(count);
    }

}
