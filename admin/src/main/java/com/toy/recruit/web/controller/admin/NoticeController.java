package com.toy.recruit.web.controller.admin;

import com.toy.recruit.core.parameter.SearchParam;
import com.toy.recruit.service.notice.NoticeService;
import com.toy.recruit.web.dto.notice.NoticeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public String main(@ModelAttribute SearchParam searchParam, Pageable pageable, Model model) {
        Page<NoticeDto> result = noticeService.findAll(searchParam, pageable);
        model.addAttribute("result", result);

        return "admin/notice/main";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute NoticeDto noticeDto) {
        return "admin/notice/add";
    }
}
