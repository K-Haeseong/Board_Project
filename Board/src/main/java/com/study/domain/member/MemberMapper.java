package com.study.domain.member;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    /* 회원 정보 저장(회원가입) */
    void save(@Param("params") MemberRequest params);

    /* ID 중복 체크(회원 수 확인) */
    int countByLoginId(String loginId);

    /* 회원 상세 정보 */
    MemberResponse findByLonginId(String loginId);

    /* 회원 정보 삭제 */
//    void deleteById(Long id);

    /* 회원 정보 수정 */
//    void update(MemberRequest params);

}
