package com.toy.recruit.core.util;

import com.toy.recruit.domain.user.Role;
import com.toy.recruit.domain.user.SessionUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static SessionUser getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal.equals("anonymousUser")) {
            return null;
        }

        return (SessionUser) principal;
    }

    public static String getLoginUserId() {
        return getLoginUser().getUserId();
    }

    public static String getRole() {
        return getLoginUser().getRole()
                .replaceAll("ROLE_", "").toLowerCase();
    }

    public static boolean isCEO() {
        return getLoginUser().getRole().equals(Role.ROLE_CEO.name());
    }

    public static boolean isEmployee() {
        return getLoginUser().getRole().equals(Role.ROLE_EMPLOYEE.name());
    }

    public static boolean isCreator(String userId) {
        return getLoginUser().getUserId().equals(userId);
    }
}
