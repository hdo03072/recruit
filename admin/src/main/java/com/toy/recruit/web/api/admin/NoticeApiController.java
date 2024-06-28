package com.toy.recruit.web.api.admin;

import com.toy.recruit.service.notice.NoticeService;
import com.toy.recruit.web.dto.notice.NoticeSave;
import com.toy.recruit.web.dto.notice.NoticeUpdate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("{id}/edit")
    public ResponseEntity update(@PathVariable Long id, @Valid NoticeUpdate noticeUpdate) {
        Long noticeId = noticeService.update(id, noticeUpdate);
        return ResponseEntity.ok(noticeId);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody Long id) {
        noticeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity deleteAll(@RequestBody List<Long> ids) {
        noticeService.deleteAll(ids);
        return ResponseEntity.ok().build();
    }
}
