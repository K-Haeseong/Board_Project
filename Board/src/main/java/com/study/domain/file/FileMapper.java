package com.study.domain.file;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    /* 파일 정보 저장 */
    void saveAll(List<FileRequest> files);

    /* 파일 목록 조회(By PostId) */
    List<FileResponse> findAllByPostId(Long postId);

    /* 파일 목록 조회(By Id) */
    List<FileResponse> findAllByIds(List<Long> ids);

}
