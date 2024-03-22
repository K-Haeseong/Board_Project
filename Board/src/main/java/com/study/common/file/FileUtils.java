package com.study.common.file;

import com.study.domain.file.FileRequest;
import com.study.domain.file.FileResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtils {

    private final String uploadPath = Path.of("C:", "Work", "upload-files").toString();
    // uploadPath의 상태 유지를 위해 파일유틸클래스의 등록 방식을 static이 아닌 Bean으로 등록
    // application.properties에서 경로를 설정 하지 않고 여기서 설정한 이유
    //  -> OS에 따라 경로 구분자가 다르기 때문에, OS에 독립적인 파일 경로를 생성 하기 위해서이다.
    // Path.of 설정시 대소문자 구분 X

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


    /* 다중 파일 삭제 (From Disk) */
    public void deleteFiles(List<FileResponse> files) {
        if( CollectionUtils.isEmpty(files) ) {
            return;
        }
        for(FileResponse file : files) {
            String uploadDate = file.getCreatedDate().format(DateTimeFormatter.ofPattern("yyMMdd"));
            deleteFile(uploadDate, file.getSaveName());
        }
    }


    /* 단일 파일 삭제 (From Disk) - 경로 추가 */
    private void deleteFile(String addPath, String filename) {
        String filePath = Path.of(uploadPath, addPath, filename).toString();
        deleteFile(filePath);
    }

    /* 파일 삭제 (From Disk) */
    private void deleteFile(String filePath) {
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }
    }


}
