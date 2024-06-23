package com.toy.recruit.core.interceptor;

import com.toy.recruit.core.util.SecurityUtils;
import com.toy.recruit.core.util.UrlComment;
import com.toy.recruit.domain.admin.Admin;
import com.toy.recruit.repository.AdminRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
@RequiredArgsConstructor
public class LogInterceptor implements HandlerInterceptor {

//    private final UsageLogRepository usageLogRepository;
    private final AdminRepository adminRepository;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String method = request.getMethod();

        if (!method.equals(HttpMethod.GET.toString())) {
            String urlComment = getUrlComment((HandlerMethod) handler);

//            if (StringUtils.hasLength(urlComment)) {
//                UsageLog usageLog = UsageLog.builder()
//                        .user(getLoginUser())
//                        .method(method)
//                        .url(request.getRequestURI())
//                        .systemNm(urlComment)
//                        .build();
//
//                usageLogRepository.save(usageLog);
//            }
        }
    }

    private String getUrlComment(HandlerMethod handlerMethod) {
        UrlComment urlComment = handlerMethod.getMethodAnnotation(UrlComment.class);

        if (urlComment != null) {
            return urlComment.value();
        } else {
            return "";
        }
    }

    private Admin getLoginUser() {
        return adminRepository.findById(SecurityUtils.getLoginUserId())
                .orElseThrow(EntityNotFoundException::new);
    }
}
