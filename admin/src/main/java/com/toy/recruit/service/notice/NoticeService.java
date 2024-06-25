package com.toy.recruit.service.notice;

import com.toy.recruit.core.parameter.SearchParam;
import com.toy.recruit.domain.notice.Notice;
import com.toy.recruit.repository.notice.NoticeRepository;
import com.toy.recruit.web.dto.notice.NoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public Page<NoticeDto> findAll(SearchParam searchParam, Pageable pageable) {
        Page<Notice> result = noticeRepository.findAll(searchParam, pageable);

        return new PageImpl<>(
                result.getContent().stream().map(NoticeDto::new).collect(Collectors.toList()),
                pageable, result.getTotalElements()
        );
    }
}
