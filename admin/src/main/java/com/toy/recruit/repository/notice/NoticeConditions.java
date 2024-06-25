package com.toy.recruit.repository.notice;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.toy.recruit.domain.notice.QNotice;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.toy.recruit.domain.notice.QNotice.notice;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NoticeConditions {

    public static BooleanExpression goeStartDate(LocalDate date) {
        if (date != null) {
            LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.MIN);
            return notice.createdAt.goe(dateTime);
        }
        return null;
    }

    public static BooleanExpression loeEndDate(LocalDate date) {
        if (date != null) {
            LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.MAX);
            return notice.createdAt.loe(dateTime);
        }
        return null;
    }

    public static BooleanExpression containWord(String word) {
        return StringUtils.hasLength(word) ? notice.title.contains(word) : null;
    }
}
