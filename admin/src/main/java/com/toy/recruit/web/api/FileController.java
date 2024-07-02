package com.toy.recruit.web.api;

import com.toy.recruit.core.util.FileUtils;
import com.toy.recruit.domain._file.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    @PostMapping("/editor")
    public ResponseEntity editorFile(@RequestPart("file") MultipartFile multipartFile) throws IOException {
        UploadFile uploadFile = FileUtils.uploadEditorFile(multipartFile);
        String fileUrl = "/files/img/" + uploadFile.getStoreFileName();

        return ResponseEntity.ok(fileUrl);
    }

    @GetMapping("/img/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        String filePath = FileUtils.getFullPath(null, filename);
        return new UrlResource("file:" + filePath);
    }
}
