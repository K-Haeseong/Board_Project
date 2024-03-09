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
        memberMapper.save(params);
        passwordEncoder.encode(params.getPassword());
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
}
