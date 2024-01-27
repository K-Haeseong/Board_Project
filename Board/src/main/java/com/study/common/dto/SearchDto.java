package com.study.common.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDto {
    
    private int page;           // 현재 페이지 번호
    private int recordSize;     // 페이지 내 출력할 데이터 개수
    private int pageSize;       // 화면에 출력할 페이지 사이즈
    private String keyword;        // 검색 키워드
    private String searchType;     // 검색 유형
//    private int offset;


    public SearchDto() {
        this.page = 1;
        this.recordSize = 10;
        this.pageSize = 10;
//        이렇게 해도 되지 않을까?
//        this.offset = (page - 1) * pageSize;
    }


    // sql 쿼리에서 Limit에 사용
    // - offset : 몇번째 데이터 부터 가져올 것인가, 0부터 시작임
    public int getOffset() {
        return (page - 1) * recordSize;
    }
}
