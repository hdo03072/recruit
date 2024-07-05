package com.toy.recruit.domain.user;

import lombok.Getter;

@Getter
public enum Role {

    ROLE_ADMIN("관리자"),
    ROLE_CEO("업체 대표"),
    ROLE_EMPLOYEE("업체 직원")
    ;

    private final String description;

    Role(String description) {
        this.description = description;
    }
}
