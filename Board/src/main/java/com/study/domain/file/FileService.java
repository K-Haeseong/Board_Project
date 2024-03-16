package com.study.domain.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileMapper fileMapper;

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

}
