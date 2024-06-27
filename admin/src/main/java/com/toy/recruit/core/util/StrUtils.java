package com.toy.recruit.core.util;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class StrUtils {

    public static String createStoreFileName(String originalFileName) {
        String ext = extractExt(originalFileName);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    public static String extractExt(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos + 1);
    }

    public static String listToString(List<String> strList) {
        return String.join("/", strList);
    }

    public static String thisYear() {
        return String.valueOf(LocalDate.now().getYear());
    }

    public static String thisMonthValue() {
        return String.valueOf(LocalDate.now().getMonthValue());
    }

    public static String thisDay() {
        return String.valueOf(LocalDate.now().getDayOfMonth());
    }

    public static String nowDate() {
        return LocalDate.now().toString();
    }

}
