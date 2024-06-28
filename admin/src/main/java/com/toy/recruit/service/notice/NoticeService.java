package com.toy.recruit.service.notice;

import com.toy.recruit.core.parameter.SearchParam;
import com.toy.recruit.core.util.FileUtils;
import com.toy.recruit.domain._file.UploadFile;
import com.toy.recruit.domain.admin.Admin;
import com.toy.recruit.domain.notice.Notice;
import com.toy.recruit.domain.notice.NoticeFile;
import com.toy.recruit.repository.notice.NoticeFileRepository;
import com.toy.recruit.repository.notice.NoticeRepository;
import com.toy.recruit.service.admin.AdminService;
import com.toy.recruit.web.dto.notice.NoticeDto;
import com.toy.recruit.web.dto.notice.NoticeSave;
import com.toy.recruit.web.dto.notice.NoticeUpdate;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.toy.recruit.core.util.FileUtils.uploadFile;
import static com.toy.recruit.core.util.FileUtils.uploadFiles;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeFileRepository noticeFileRepository;
    private final AdminService adminService;

    /*목록 조회*/
    public Page<NoticeDto> findAll(SearchParam searchParam, Pageable pageable) {
        Page<Notice> result = noticeRepository.findAll(searchParam, pageable);

        return new PageImpl<>(
                result.getContent().stream().map(NoticeDto::new).collect(Collectors.toList()),
                pageable, result.getTotalElements()
        );
    }

    /*다중 조회_1 엔티티*/
    /*다중 조회_2 dto*/

    /*단일 조회_1 엔티티*/
    public Notice findById(Long id) {
        return noticeRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    /*단일 조회_2 dto*/
    public NoticeDto getNotice(Long id) {
        Notice notice = findById(id);
        return new NoticeDto(notice);
    }

    public NoticeFile findFile(Long fileId) {
        return noticeFileRepository.findById(fileId)
                .orElseThrow(EntityNotFoundException::new);
    }

    /*저장*/
    @Transactional
    public Long save(NoticeSave data) {
        Notice notice = setNotice(data);
        return noticeRepository.save(notice).getId();
    }

    private Notice setNotice(NoticeSave data) {
        Admin loginAdmin = adminService.findLoginAdmin();
        List<UploadFile> files = newFileList(data.getFiles());
        return Notice.of(data, loginAdmin, files);
    }

    /*수정*/
    @Transactional
    public Long update(Long id, NoticeUpdate data) {
        Notice notice = findById(id);
        notice.update(data);
        return id;
    }

    /*선택 삭제*/
    @Transactional
    public void deleteAll(List<Long> ids) {
        ids.forEach(this::delete);
    }

    /*삭제*/
    @Transactional
    public void delete(Long id) {
        // 서버에 존재하는 파일 삭제
        Notice notice = findById(id);
        deleteFile(notice.getFiles());

        // db에서 삭제
        noticeRepository.deleteById(id);
    }

    /*단일 파일 변환 MultiPartFile -> uploadFile*/
    private UploadFile newFile(MultipartFile file) throws IOException {
        return uploadFile(file, "notice");
    }

    /*다중 파일 변환 MultiPartFile -> uploadFile*/
    private List<UploadFile> newFileList(List<MultipartFile> files) {
        // notice: 파일 디렉토리 명
        return uploadFiles(files, "notice");
    }

    /*서버 파일 삭제*/
    private void deleteFile(List<NoticeFile> files) {
        if (files.size() > 0) {
            files.forEach(file -> FileUtils.deleteFile(file.getPath(), file.getStoreFileName()));
        }
    }

    /*기타*/
}
