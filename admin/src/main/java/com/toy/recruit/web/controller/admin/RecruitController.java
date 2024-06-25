package com.toy.recruit.web.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/recruit")
@RequiredArgsConstructor
public class RecruitController {

    @GetMapping
    public String main(Model model) {
        return "admin/recruit/main";
    }
}
