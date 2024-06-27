package com.toy.recruit.core.util;

import com.toy.recruit.domain._file.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.toy.recruit.core.util.StrUtils.*;

@Component
public class FileUtils {

    private static String fileDir;

    @Value("${file.dir}")
    public void setFileDir(String value) {
        fileDir = value;
    }

    public static UploadFile uploadFile(MultipartFile file, String pathName) throws IOException {
        if (file == null) return null;
        if (!StringUtils.hasLength(pathName)) return null;

        String year = String.valueOf(LocalDate.now().getYear());
        String month = String.valueOf(LocalDate.now().getMonthValue());

        String originFileName = file.getOriginalFilename();
        String storeFileName = createStoreFileName(originFileName);
        String path = listToString(List.of(fileDir, pathName, year, month));
        String contentType = file.getContentType();
        String extension = extractExt(originFileName);
        Long fileSize = file.getSize();

        if (createFile(file, path, storeFileName)) {
            return UploadFile.builder()
                    .originFileName(originFileName)
                    .storeFileName(storeFileName)
                    .path(path)
                    .contentType(contentType)
                    .extension(extension)
                    .fileSize(fileSize)
                    .build();
        }

        return null;
    }

    public static List<UploadFile> uploadFiles(List<MultipartFile> files, String pathName) {
        List<UploadFile> uploadFiles = new ArrayList<>();
        files.forEach(file -> {
            UploadFile uploadFile = null;
            try {
                uploadFile = uploadFile(file, pathName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            uploadFiles.add(uploadFile);
        });

        return uploadFiles;
    }

    public static void deleteFile(String pathName, String fileName) {
        if (!StringUtils.hasLength(pathName)) return;
        if (!StringUtils.hasLength(fileName)) return;

        String fullPath = listToString(List.of(pathName, "/", fileName));
        File file = new File(fullPath);

        if (file.exists()) {
            file.delete();
        }
    }

    public static ResponseEntity download(String originFileName, String storeFileName, String path, String contentType) throws MalformedURLException {
        UrlResource resource = new UrlResource("file:" + listToString(List.of(path, "/", storeFileName)));
        String encodedFileName = UriUtils.encode(originFileName, StandardCharsets.UTF_8);
        String disposition = "attachment; filename=\"" + encodedFileName + "\"";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, disposition);
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, contentType);
        httpHeaders.add("filename", encodedFileName);

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(resource);
    }

    private static boolean createFile(MultipartFile multipartFile, String path, String storeFileName) throws IOException {
        if (multipartFile == null) return false;
        if (!StringUtils.hasLength(path)) return false;

        // 폴더 생성
        File file = new File(listToString(List.of(path, "/", storeFileName)));
        if (!file.exists()) {
            file.mkdirs();
        }

        // 경로에 파일 저장
        multipartFile.transferTo(file);
        return file.exists();
    }
}
