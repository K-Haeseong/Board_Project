package com.study.domain.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    /* 회원 정보 저장 */
    void memberSave(MemberRequest params);

    /* ID 중복 체크(회원 수 확인) */
    int countByLoginId(String loginId);

    /* 회원 상세 정보 */
    MemberResponse findByLonginId(String loginId);

}
