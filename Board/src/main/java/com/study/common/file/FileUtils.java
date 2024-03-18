package com.study.common.file;

import com.study.domain.file.FileRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtils {

    private final String uploadPath = Path.of("C:", "work", "upload-files").toString();


    /* 다중 파일 업로드 */
    public List<FileRequest> uploadFiles(List<MultipartFile> multipartFiles) {

        List<FileRequest> files = new ArrayList<>();

        for ( MultipartFile multipartFile : multipartFiles ) {
            if( multipartFile.isEmpty() ) {
                continue;
            }
            files.add(uploadFile(multipartFile));
        }

        return files;
    }


    /* 단일 파일 업로드 */
    public FileRequest uploadFile(MultipartFile multipartFile) {

        if(multipartFile.isEmpty()) {
            return null;
        }

        String saveName = generateSaveFilename(multipartFile.getOriginalFilename());
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd")).toString();
        System.out.println(today);

        String today123 = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        System.out.println(today123);

        String uploadPath = getUploadPath(today) + File.separator + saveName;
        File uploadFile = new File(uploadPath);

        try {
            multipartFile.transferTo(uploadFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return FileRequest.builder()
                .originalName(multipartFile.getOriginalFilename())
                .saveName(saveName)
                .size(multipartFile.getSize())
                .build();
    }


    /* 저장 파일명 생성 */
    private String generateSaveFilename(String fileName) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String extension = StringUtils.getFilenameExtension(fileName);
        return uuid + "." + extension;
    }


    /* 업로드 경로 반환 */
    private String getUploadPath() {
        return makeDirectories(uploadPath);
    }

    /* 경로 추가 후 업로드 경로 반환 */
    private String getUploadPath(String addPath) {
        return makeDirectories(uploadPath + File.separator + addPath);
    }

    /* 업로드 폴더 생성 */
    private String makeDirectories(String path) {
        File dir = new File(path);
        if(dir.exists() == false) {
            dir.mkdirs();
        }
        return dir.getPath();
    }

}
