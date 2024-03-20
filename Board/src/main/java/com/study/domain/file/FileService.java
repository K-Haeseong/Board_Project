package com.study.domain.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileMapper fileMapper;

    /* 파일 저장 */
    @Transactional
    public void saveFiles(Long postId, List<FileRequest> files) {
        if(CollectionUtils.isEmpty(files)) {
            return;
        }
        for (FileRequest file : files) {
            file.setPostId(postId);
        }
        fileMapper.saveAll(files);
    }

    /* 파일 목록 조회(By PostId) */
    public List<FileResponse> findAllFileByPostId(Long postId) {
        return fileMapper.findAllByPostId(postId);
    }

    /* 파일 목록 조회(By Id) */
    public List<FileResponse> findAllFileByIds(List<Long> ids) {
        if ( CollectionUtils.isEmpty(ids) ) {
            return Collections.emptyList();
        }
        return fileMapper.findAllByIds(ids);
    }

}
