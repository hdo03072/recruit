package com.toy.recruit.core.util;

public interface EnumNameParser {
    String getDescription();
    String name();

    default String nameToUrl() {
        return name().toLowerCase()
                .replace("_", "-");
    }
}
