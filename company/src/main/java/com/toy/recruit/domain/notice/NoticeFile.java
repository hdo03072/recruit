package com.toy.recruit.domain.notice;

import com.toy.recruit.domain._file.File;
import com.toy.recruit.domain._file.UploadFile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "notice_file")
public class NoticeFile extends File {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private Notice notice;

    /*연관관계 메소드*/
    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public static NoticeFile of(UploadFile file) {
        NoticeFile noticeFile = new NoticeFile();
        noticeFile.originFileName = file.getOriginFileName();
        noticeFile.storeFileName = file.getStoreFileName();
        noticeFile.path = file.getPath();
        noticeFile.contentType = file.getContentType();
        noticeFile.extension = file.getExtension();
        noticeFile.fileSize = file.getFileSize();
        return noticeFile;
    }

}
