package com.toy.recruit.core.util;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PagingUtils {

    public static <T> Page<T> getPage(JPAQuery<T> query, Pageable pageable) {
        List<T> content = applyPagination(pageable, query.clone()).fetch();
        return PageableExecutionUtils.getPage(content, pageable, query.fetch()::size);
    }

    private static <T> JPQLQuery<T> applyPagination(Pageable pageable, JPQLQuery<T> query) {
        if (pageable.isUnpaged()) {
            return query;
        }

        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());

        return query;
    }
}
