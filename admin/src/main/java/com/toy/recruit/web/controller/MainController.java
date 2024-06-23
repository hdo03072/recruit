package com.toy.recruit.web.controller;

import com.toy.recruit.core.util.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String redirect() {
        if (SecurityUtils.getLoginUser() == null) {
            return "redirect:/login";
        }

        return "redirect:/admin";
    }
}
