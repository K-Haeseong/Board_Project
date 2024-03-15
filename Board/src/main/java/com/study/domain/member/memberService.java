package com.study.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class memberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;

    /* 회원 정보 저장(회원가입) */
    @Transactional
    public Long saveMember(MemberRequest params) {
        params.encodingPassword(passwordEncoder);
        memberMapper.save(params);
        return params.getId();
    }

    /* 회원 상세 정보 */
    public MemberResponse findMemberByLoginId(String loginId) {
        return memberMapper.findByLonginId(loginId);
    }

    /* 회원 수 세기(중복체크) */
    public int countMemberByLoginId(String loginId) {
        return memberMapper.countByLoginId(loginId);
    }


    // 로그인
    public MemberResponse login(String loginId, String password) {

        // 회원 정보 및 비밀번호 조회
        MemberResponse member = findMemberByLoginId(loginId);
        String encodedPassword = (member == null) ? "" : member.getPassword();

        // 회원 정보 및 비밀번호 체크
        if ( member == null || passwordEncoder.matches(password, encodedPassword) == false) {
            return null;
        }

        member.clearPassword();
        return member;

    }


}
