package com.toy.recruit.domain.notice;

import com.toy.recruit.core.domain.BaseEntity;
import com.toy.recruit.core.util.FileUtils;
import com.toy.recruit.domain._file.UploadFile;
import com.toy.recruit.domain.admin.Admin;
import com.toy.recruit.web.dto.notice.NoticeSave;
import com.toy.recruit.web.dto.notice.NoticeUpdate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    /*연관관계 메소드*/
    private static List<NoticeFile> addFiles(Notice notice, List<UploadFile> files) {
        List<NoticeFile> noticeFiles = new ArrayList<>();
        files.forEach(file -> {
            NoticeFile noticeFile = NoticeFile.of(file);
            noticeFile.setNotice(notice);
            noticeFiles.add(noticeFile);
        });

        return noticeFiles;
    }

    /*비즈니스 메소드*/
    public void update(NoticeUpdate data) {
        this.title = data.getTitle();
        this.content = data.getContent();

        if (data.getFiles().size() > 0) {
            updateFiles(data);
        }
    }

    private void updateFiles(NoticeUpdate data) {
        List<MultipartFile> updateFiles = data.getFiles();
        List<Long> savedFileIds = data.getSavedFileIds();
        List<NoticeFile> resultList = new ArrayList<>();

        // 서버에 저장된 파일 삭제
        this.files.forEach(file -> {
            Optional<Long> savedFileId = savedFileIds.stream().filter(ids -> file.getId().equals(ids)).findAny();

            if (savedFileId.isEmpty()) {
                FileUtils.deleteFile(file.getPath(), file.getStoreFileName());
            } else {
                resultList.add(file);
            }
        });

        // 서버에 저장할 파일 저장
        updateFiles.forEach(file -> {
            try {
                UploadFile uploadFile = FileUtils.uploadFile(file, "notice");
                NoticeFile noticeFile = NoticeFile.of(uploadFile);
                noticeFile.setNotice(this);
                resultList.add(noticeFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // 디비에 저장된 데이터 전체 삭제
        this.files.clear();
        // 디비에 데이터 저장
        this.files.addAll(resultList);
    }
}
