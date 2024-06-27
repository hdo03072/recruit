package com.toy.recruit.web.controller.admin;

import com.toy.recruit.core.parameter.SearchParam;
import com.toy.recruit.core.util.FileUtils;
import com.toy.recruit.domain.notice.NoticeFile;
import com.toy.recruit.service.notice.NoticeService;
import com.toy.recruit.web.dto.notice.NoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequestMapping("/admin/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public String main(@ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {
        Page<NoticeDto> result = noticeService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "admin/notice/main";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        NoticeDto notice = noticeService.getNotice(id);
        model.addAttribute("notice", notice);
        return "admin/notice/detail";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute NoticeDto noticeDto) {
        return "admin/notice/add";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        NoticeDto notice = noticeService.getNotice(id);
        model.addAttribute("notice", notice);
        return "admin/notice/edit";
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity download(@PathVariable Long fileId) throws MalformedURLException {
        NoticeFile file = noticeService.findFile(fileId);
        String originFileName = file.getOriginFileName();
        String storeFileName = file.getStoreFileName();
        String path = file.getPath();
        String contentType = file.getContentType();

        return FileUtils.download(originFileName, storeFileName, path, contentType);
    }

    @GetMapping("/zip/{noticeId}")
    public ResponseEntity zip(@PathVariable Long noticeId) throws FileNotFoundException {
        List<NoticeFile> files = noticeService.findById(noticeId).getFiles();
        if (files.size() > 0) {
            throw new FileNotFoundException("파일을 찾을 수 없습니다.");
        }

//        files.forEach(file -> {
//            UploadFile uploadFile = UploadFile.builder()
//                    .originFileName(file.getOriginFileName())
//                    .storeFileName(file.getStoreFileName())
//                    .path(file.getPath())
//                    .contentType(file.getContentType())
//                    .extension(file.getExtension())
//                    .fileSize(file.getFileSize())
//                    .build();
//
//        });

        return null;
    }
}
