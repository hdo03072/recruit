package com.toy.recruit.repository.notice;

import com.toy.recruit.core.parameter.SearchParam;
import com.toy.recruit.domain.notice.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeRepositoryQueryDsl {

    Page<Notice> findAll(SearchParam searchParam, Pageable pageable);
}
