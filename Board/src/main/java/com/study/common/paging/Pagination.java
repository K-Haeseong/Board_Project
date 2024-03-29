package com.study.common.paging;

import com.study.common.dto.SearchDto;
import lombok.Getter;

@Getter
public class Pagination {

    private int totalRecordCount;   // 전체 게시글 수
    private int totalPageCount;     // 전체 페이지 수
    private int startPage;          // 해당 페이지의 첫 페이지
    private int endPage;            // 해당 페이지의 끝 페이지
    private int limitStart;         // Limit 시작 위치
    private boolean existPrevPage;  // 이전 페이지 유무
    private boolean existNextPage;  // 다음 페이지 유무

    public Pagination(int totalRecordCount, SearchDto params) {
        if (totalRecordCount > 0) {
            this.totalRecordCount = totalRecordCount;
            calculation(params);
            // Service 클래스의 findAll 메서드 내 params.setPagination(pagination); 코드
            // 페이지네이션에서 공통으로 처리
            // 코드 리펙토링
            params.setPagination(this);
        }
    }

    private void calculation(SearchDto params) {

        // 전체 페이지 수 계산
        totalPageCount = ((totalRecordCount - 1) / params.getRecordSize()) + 1;
        // totalPageCount = (totalRecordCount % params.getRecordSize()) == 0 ? totalPageCount : totalPageCount + 1;

        // 현재 페이지 번호가 전체 페이지 수보다 큰 경우, 현재 페이지 번호에 전체 페이지 수 저장
        if(params.getPage() > totalPageCount) {
            params.setPage(totalPageCount);
        }

        // 첫 페이지 번호 계산
        startPage = ((params.getPage() - 1) / params.getPageSize()) * params.getPageSize() + 1;

        // 끝 페이지 번호 계산
        endPage = startPage + params.getPageSize() - 1;

        // 끝 페이지가 전체 페이지 수보다 큰 경우, 전체 페이지 수가 끝 페이지가 되도록 저장
        if (endPage > totalPageCount) {
            endPage = totalPageCount;
        }

        // LIMIT 시작 위치 계산
        limitStart = (params.getPage() -1) * params.getRecordSize();

        // 이전 페이지 존재 여부 확인
        existPrevPage = startPage != 1;

        // 다음 페이지 존재 여부 확인
        existNextPage = endPage * params.getRecordSize() < totalRecordCount;

    }

}
