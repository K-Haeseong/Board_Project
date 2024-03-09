package com.study.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRequest {

    private Long id;                // 회원 번호(PK)
    private String loginId;         // 로그인 아이디
    private String password;        // 비밀번호
    private String name;            // 이름
    private Gender gender;          // 성별
    private LocalDate birthday;     // 성년월일

    // 비밀번호 암호화
    public void encodingPassword(PasswordEncoder passwordEncoder) {
        if( StringUtils.isEmpty(password) ) {
            return;
        }
        password = passwordEncoder.encode(password);
        // PasswordEncoder의 encode( )는 파라미터로 전달한 값을 60 자리의 난수로 리턴
        //  -> DB의 password 길이가 60인 이유
    }
}
