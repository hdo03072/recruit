package com.toy.recruit.domain.notice;

import com.toy.recruit.core.domain.BaseEntity;
import com.toy.recruit.domain._file.UploadFile;
import com.toy.recruit.domain.admin.Admin;
import com.toy.recruit.web.dto.notice.NoticeSave;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.toy.recruit.core.code.Constants.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "notice")
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @Column(length = DB_MAX_NUM, name = "title", nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = TEXT)
    private String content;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NoticeFile> files = new ArrayList<>();

    /*생성 메소드*/
    public static Notice of(NoticeSave data, Admin admin, List<UploadFile> files) {
        Notice notice = new Notice();
        notice.admin = admin;
        notice.title = data.getTitle();
        notice.content = data.getContent();

        if (files.size() > 0) {
            notice.files = addFiles(notice, files);
        }

        return notice;
    }

    private static List<NoticeFile> addFiles(Notice notice, List<UploadFile> files) {
        List<NoticeFile> noticeFiles = new ArrayList<>();
        files.forEach(file -> {
            NoticeFile noticeFile = NoticeFile.of(file);
            noticeFile.setNotice(notice);
            noticeFiles.add(noticeFile);
        });

        return noticeFiles;
    }
}
