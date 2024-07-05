package com.toy.recruit.web.controller;

import com.toy.recruit.core.util.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

    @GetMapping("/")
    public String redirect(RedirectAttributes redirectAttributes) {
        if (SecurityUtils.getLoginUser() == null) {
            return "redirect:/login";
        } else {
            String role = SecurityUtils.getRole();
            redirectAttributes.addAttribute("role", role);
            return "redirect:/{role}";
        }
    }
}
