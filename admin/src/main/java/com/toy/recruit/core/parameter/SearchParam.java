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

    private String searchWorld;

    private Integer listCount;

//
//    public SearchParam() {
//        this.startDate = LocalDate.of(LocalDate.now().getYear(), 1, 1);
//        this.endDate = LocalDate.of(LocalDate.now().getYear(), 12, 31);
//        this.yearMonth = YearMonth.of(YearMonth.now().getYear(), YearMonth.now().getMonth());
//        this.selectKey = getSelectKey();
//        this.selectKey2 = getSelectKey2();
//        this.searchWorld = getSearchWorld();
//        this.listCount = getListCount();
//    }
}
