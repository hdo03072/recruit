package com.toy.recruit.web.api.admin;

import com.toy.recruit.service.notice.NoticeService;
import com.toy.recruit.web.dto.notice.NoticeSave;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/notice")
@RequiredArgsConstructor
public class NoticeApiController {

    private final NoticeService noticeService;

    @PostMapping("/add")
    public ResponseEntity save(@Valid NoticeSave noticeSave) {
        Long id = noticeService.save(noticeSave);
        return ResponseEntity.ok(id);
    }
}
