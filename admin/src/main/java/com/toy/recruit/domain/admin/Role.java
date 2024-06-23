package com.toy.recruit.domain.admin;

import lombok.Getter;

@Getter
public enum Role {

    ROLE_ADMIN("관리자");

    private final String description;

    Role(String description) {
        this.description = description;
    }
}
