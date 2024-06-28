package com.toy.recruit.core.util;

import jakarta.servlet.http.HttpServletRequest;

public class CommonUtils {

    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("x-requested-with"));
    }
}
