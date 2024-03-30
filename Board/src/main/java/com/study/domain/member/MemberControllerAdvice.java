package com.study.domain.member;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = {MemberController.class})
public class MemberControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<String> handleDuplicateMemberException(DuplicateMemberException e){
        return ResponseEntity
                .internalServerError()
                .body("중복 아이디 존재");
    }

}
