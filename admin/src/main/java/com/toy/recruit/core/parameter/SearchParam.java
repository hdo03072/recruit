package com.toy.recruit.core.parameter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.YearMonth;

@Getter @Setter
public class SearchParam {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private YearMonth yearMonth;

    private String selectKey;

    private String selectKey2;

    private String searchWord;
}
