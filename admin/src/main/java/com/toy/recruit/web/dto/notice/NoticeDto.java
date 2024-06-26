package com.toy.recruit.web.dto.notice;

import com.toy.recruit.domain.admin.Admin;
import com.toy.recruit.domain.notice.Notice;
import com.toy.recruit.domain.notice.NoticeFile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NoticeDto {

    private Long id;

    private Admin admin;

    private String title;

    private String content;

    private List<NoticeFile> files;

    private LocalDateTime createdAt;

    public NoticeDto(Notice notice) {
        this.id = notice.getId();
        this.admin = notice.getAdmin();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.createdAt = notice.getCreatedAt();
        this.files = notice.getFiles();
    }
}
