package com.toy.recruit.repository.notice;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.toy.recruit.core.parameter.SearchParam;
import com.toy.recruit.core.util.PagingUtils;
import com.toy.recruit.domain.notice.Notice;
import com.toy.recruit.domain.notice.QNotice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

import static com.toy.recruit.domain.notice.QNotice.notice;
import static com.toy.recruit.repository.notice.NoticeConditions.*;

@RequiredArgsConstructor
public class NoticeRepositoryImpl implements NoticeRepositoryQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Notice> findAll(SearchParam searchParam, Pageable pageable) {
        LocalDate startDate = searchParam.getStartDate();
        LocalDate endDate = searchParam.getEndDate();
        String word = searchParam.getSearchWord();

        JPAQuery<Notice> query = queryFactory
                .selectFrom(notice)
                .where(
                        goeStartDate(startDate),
                        loeEndDate(endDate),
                        containWord(word)
                )
                .orderBy(notice.id.desc());

        return PagingUtils.getPage(query, pageable);
    }
}
